/**
 * 
 */
package com.cognizant.fecodegen.components.render;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author 238209
 *
 */
public class ReactComponentRenderer extends BaseRenderer {

	public static String PREFIX = "codegen.react"; 
	
	public ReactComponentRenderer(Properties properties) {
		super(PREFIX, properties);
	}

	@Override
	public boolean render(JsonDocument jsonDoc) throws CodeGenException {
		//super.render(jsonDoc);
		System.out.println(jsonDoc);
		JsonArray uiLayout = jsonDoc.getJsonArray("layout");

		uiLayout.forEach(element -> {
			try {
				StringBuilder content = new StringBuilder();

				generateCode(element, content);
				
				write(content.toString());
			} catch (CodeGenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return false;
	}

	/**
	 * @param element
	 * @param content
	 */
	private void generateCode(JsonElement element, StringBuilder content) {
		JsonObject obj = element.getAsJsonObject();

		String type = obj.get("type").getAsString();
		
		if ("section".equalsIgnoreCase(type)) {
			try {
				String tempName = getTemplateName(type);
				Map<String, String> contextVariables = getParametersMap(content, obj, tempName);
				contextVariables.put("imports", "import panel from 'react-bootstrap';");
				
				Map<String, String> panelVariables = new HashMap<String, String>();
				//panelVariables.put("htmlId", htmlId);
				panelVariables.put("eventKey", "1");
				panelVariables.put("bsStyle", "primary");
				//panelVariables.put("panelTitle", label);
				panelVariables.put("panelBody", "Test");

				String componentTemplate = parser.parse(templateName, contextVariables);


				String panelTemplate = parser.parse("fm/panel.ftl", panelVariables);
				
				contextVariables.put("render", panelTemplate);
				
				//outFilename = compLabel + ".js";
			} catch (CodeGenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getTemplateName(String type) {
		JsonObject templates = config.getJsonObject("config").get("templates").getAsJsonObject();
		
		return templates.get(type).getAsString();
	}

	private Map<String, String> getParametersMap(StringBuilder content, JsonObject obj, String templateName) 
			throws CodeGenException {
		
		String rawTemplate = parser.read(templateName);
		
		Map<String, String> contextVariables = new HashMap<String, String>();
		String[] paramsArray = StringUtils.substringsBetween(rawTemplate, "${", "}");
		for (String param : paramsArray) {
			String key = StringUtils.substringBefore(param, "#");
			JsonElement element = obj.get(key); 
			
			if (element != null) {
				String value = element.getAsString();
				
				String operation = StringUtils.substringAfter(param, "#");
				if (StringUtils.equalsIgnoreCase(operation, "stripWhiteSpace")) {
					value = StringUtils.replace(value, " ", "");
				}
				
				contextVariables.put(param, value);
			} else {
				contextVariables.put(param, null);
			}
		}
		
		return contextVariables;
	}

}
