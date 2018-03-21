/**
 * 
 */
package com.cognizant.fecodegen.components;

import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.components.render.IRenderer;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.cognizant.fecodegen.service.RendererFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author 238209
 *
 */
public class ReactJsGenerator extends CodeGenerator {

	@Override
	public boolean generate() throws CodeGenException {
		/*Map<String, String> inputs = new HashMap<String, String>();
		inputs.put("SectionName", "App");
		inputs.put("reactBootstrap", "react-bootstrap");
		
		String response = parser.parse("fm/reactcomponent.ftl", inputs);*/
		//JsonDocument jsonDoc = JsonUtils.getMockJsonDocument();
		
		JsonArray layout = configJson.getJsonArray("layout");
		layout.forEach(je -> {
			JsonObject config = je.getAsJsonObject();
			String type = config.get("type").getAsString();
			try {
				IRenderer renderer = RendererFactory.getRenderer(type, request.getCodeGenPath(), configJson);
				
				renderer.initializeParams(new JsonDocument(config));
				renderer.render(new JsonDocument(config.get("params").getAsJsonObject()));
			} catch (CodeGenException e) {
				e.printStackTrace();
			}
		});
		
		/*Set<Entry<String, JsonElement>> entrySet = configJson.getJson().entrySet();
		entrySet.forEach(entry -> {
			if (entry.get)
			pjRenderer.render(jsonDoc);
		});*/
		
		//System.out.println(response);

		return false;
	}

}
