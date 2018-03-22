/**
 * 
 */
package com.cognizant.fecodegen.bo;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author 238209
 *
 */
public class JsonDocument {
	
	private JsonObject json;
	
	public JsonDocument(JsonObject json) {
		this.json = json;
	}

	public JsonObject getJsonObject(String keyName) {
		JsonElement element = json.get(keyName);
		if (element!= null && element.isJsonObject()) {
			return element.getAsJsonObject();
		}
		
		return null;
	}
	
	public JsonArray getJsonArray(String keyName) {
		JsonElement element = json.get(keyName);
		if (element!= null && element.isJsonArray()) {
			return element.getAsJsonArray();
		}
		
		return null;
	}
	
	public String getAsString(String keyName) {
		JsonElement element = json.get(keyName);
		if (element!= null && element.isJsonPrimitive()) {
			return element.getAsString();
		}
		
		return null;
	}


	public Map<String, String> getAsMap() {
		Map<String, String> params = new HashMap<String, String>();
		
		json.entrySet().forEach(e -> {
			params.put(e.getKey(), e.getValue().getAsString());
		});
		
		return params;
	}

	/**
	 * @return the json
	 */
	public JsonObject getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(JsonObject json) {
		this.json = json;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (json != null) {
			return json.toString();
		}
		
		return "Null JSON object";
	}
}
