/**
 * 
 */
package com.cognizant.fecodegen.components.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.cognizant.fecodegen.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author 238209
 *
 */
public class ReactComponentRenderer extends BaseRenderer {

	private static Logger LOGGER = Logger.getLogger(ReactComponentRenderer.class);
	
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

				generateCode(null, element, content);
				
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
	private void generateCode(JsonElement parentElement, JsonElement element, StringBuilder content) {
		
		if (hasChildren(element)) {
			generateCode (element, getChild(element), content);
		}
		
		if (element.isJsonObject()) {
			JsonObject obj = element.getAsJsonObject();

			String type = obj.get("type").getAsString();
			String tempName = getTemplateName(type);

			try {
				Map<String, String> contextVariables = getParametersMap(content, obj, tempName, false);
				if (contextVariables.containsKey("childComponent")) {
					contextVariables.put("childComponent", content.toString());
					content.delete(0, content.length());
				}

				if ("section".equalsIgnoreCase(type)) {
					contextVariables.put("imports", "import panel from 'react-bootstrap';");

					outFilename = contextVariables.get("label_stripWhiteSpace") + ".js";
				} else if ("textbox".equalsIgnoreCase(type)) {

				}
				
				content.append(parser.parse(tempName, contextVariables));
			} catch (CodeGenException e) {
				LOGGER.error("Error while parsing and rendering template: " + tempName, e);
			}

		} else if (element.isJsonArray()) {
			JsonArray childArray = element.getAsJsonArray();
			JsonObject obj = parentElement.getAsJsonObject();
			
			String type = "array";
			String tempName = getTemplateName(type);
			
			try {
				Map<String, String> contextVariables = getParametersMap(content, obj, tempName, true);
				
				childArray.forEach(child -> {
					generateCode(parentElement, child, content);
				});
				
				if (contextVariables.containsKey("childComponent")) {
					contextVariables.put("childComponent", content.toString());
					content.delete(0, content.length());
				}
				
				content.append(parser.parse(tempName, contextVariables));
			} catch (CodeGenException e) {
				LOGGER.error("Error while parsing and rendering template: " + tempName, e);
			}
		}
	}

	private boolean hasChildren(JsonElement element) {
		boolean hasChild = false;
		
		if (element.isJsonObject()) {
			JsonObject obj = element.getAsJsonObject();
			
			if (obj.get("columns") != null) {
				hasChild = true;
			}
		}
		
		return hasChild;
	}
	
	private JsonElement getChild(JsonElement element) {
		JsonElement child = null;
		
		if (element.isJsonObject()) {
			JsonObject obj = element.getAsJsonObject();
			
			child = obj.get("columns");
		}
		
		return child;
	}

	private String getTemplateName(String type) {
		JsonObject templates = config.getJsonObject("config").get("templates").getAsJsonObject();
		
		return templates.get(type).getAsString();
	}

	private Map<String, String> getParametersMap(StringBuilder content, JsonObject obj, 
			String templateName, boolean appendRandomId) 
			throws CodeGenException {
		LOGGER.debug("Reading template = " + templateName);
		String rawTemplate = parser.read(templateName, true);
		
		// Get the replaceable parameters defined in the template
		String[] paramsArray = StringUtils.substringsBetween(rawTemplate, "${", "}");

		Set<Integer> generatedRandom = new HashSet<Integer>();
		// Set values for the parameters from the input UI layout / configuration
		Map<String, String> contextVariables = new HashMap<String, String>();
		for (String param : paramsArray) {
			String key = StringUtils.substringBefore(param, "_");
			JsonElement element = obj.get(key); 
			
			if (element != null) {
				String value = element.getAsString();
				
				String operation = StringUtils.substringAfter(param, "_");
				if (StringUtils.equalsIgnoreCase(operation, "stripWhiteSpace")) {
					value = StringUtils.replace(value, " ", "");
				}
				
				if (appendRandomId) {
					value = value + JsonUtils.getRandomNumber(generatedRandom);
				}
				
				contextVariables.put(param, value);
			} else {
				contextVariables.put(param, null);
			}
		}
		
		return contextVariables;
	}

}
