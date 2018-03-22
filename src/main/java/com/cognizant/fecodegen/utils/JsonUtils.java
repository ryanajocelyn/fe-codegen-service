/**
 * 
 */
package com.cognizant.fecodegen.utils;

import java.util.Random;
import java.util.Set;

import com.cognizant.fecodegen.bo.JsonDocument;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author 238209
 *
 */
public class JsonUtils {
	
	public static JsonDocument getJsonObject(String jsonString) {
		JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);
		
		return new JsonDocument(jobj);
		/*JsonArray ja = jobj.get("layout").getAsJsonArray();
		
		ja.forEach(el -> {
			System.out.println("type: " + el.getAsJsonObject().get("type").getAsString());
			
			JsonArray jo = el.getAsJsonObject().get("columns").getAsJsonArray();
			System.out.println(jo);
			jo.forEach(cl -> {
				System.out.println(cl);
				System.out.println(cl.isJsonArray());
				cl.getAsJsonArray().forEach(col -> {
					col.getAsJsonObject().entrySet().stream().forEach(qm -> {
						String key = qm.getKey();
						System.out.println("key: " + key);
					});
				});
			});
		});*/
	}
	
	public static Integer getRandomNumber(Set<Integer> generatedRandom) {
		Random random = new Random();
		
		int randomNum = 0;
		do {
			randomNum = random.nextInt(100);
		} while (generatedRandom.add(randomNum) != true);
		
		return randomNum;
	}
	
	public static JsonDocument getMockJsonDocument() {
		String jsonString = "{\"layout\":[{\"type\":\"section\",\"id\":\"4\",\"section\":\"section\",\"label\":\"Leave Request Form\",\"htmlID\":\"htmlID\",\"columns\":[[{\"type\":\"textbox\",\"id\":4,\"label\":\"Leave Reason\",\"htmlID\":\"vacationReason\",\"mandatory\":true,\"length\":\"20\"},{\"type\":\"datepicker\",\"id\":7,\"options\":[],\"label\":\"Leave Start Date\",\"htmlID\":\"vacationStartDate\"},{\"type\":\"datepicker\",\"id\":8,\"options\":[],\"label\":\"Leave End Date\",\"htmlID\":\"vacationEndDate\"},{\"type\":\"dropdown\",\"id\":6,\"options\":[{\"display\":\"Sick Leave\",\"value\":\"sickLeave\",\"htmlids\":\"vacationType_1\"},{\"display\":\"Personal Leave\",\"value\":\"personalLeave\",\"htmlids\":\"vacationType_2\"}],\"label\":\"Vacation Type\",\"htmlID\":\"vacationType\",\"tempdisplay\":\"\",\"tempoption\":\"\"}]]}]}";
		
		return getJsonObject(jsonString);
	}
	
	public static void main(String[] args) {
		getMockJsonDocument();
	} 
	
	public static void test() {
		String jsonString = "{\"layout\":[{\"type\":\"section\",\"id\":\"4\",\"section\":\"section\",\"label\":\"Leave Request Form\",\"htmlID\":\"htmlID\",\"columns\":[[{\"type\":\"textbox\",\"id\":4,\"label\":\"Leave Reason\",\"htmlID\":\"vacationReason\",\"mandatory\":true,\"length\":\"20\"},{\"type\":\"datepicker\",\"id\":7,\"options\":[],\"label\":\"Leave Start Date\",\"htmlID\":\"vacationStartDate\"},{\"type\":\"datepicker\",\"id\":8,\"options\":[],\"label\":\"Leave End Date\",\"htmlID\":\"vacationEndDate\"},{\"type\":\"dropdown\",\"id\":6,\"options\":[{\"display\":\"Sick Leave\",\"value\":\"sickLeave\",\"htmlids\":\"vacationType_1\"},{\"display\":\"Personal Leave\",\"value\":\"personalLeave\",\"htmlids\":\"vacationType_2\"}],\"label\":\"Vacation Type\",\"htmlID\":\"vacationType\",\"tempdisplay\":\"\",\"tempoption\":\"\"}]]}]}";
		JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);
		JsonArray ja = jobj.get("layout").getAsJsonArray();
		ja.forEach(el -> {
			System.out.println("product: " + el.getAsJsonObject().get("product").getAsString());
			JsonObject jo = el.getAsJsonObject().get("question_mark").getAsJsonObject();            
			jo.entrySet().stream().forEach(qm -> {
				String key = qm.getKey();
				JsonElement je = qm.getValue();
				System.out.println("key: " + key);
				JsonObject o = je.getAsJsonObject();
				o.entrySet().stream().forEach(prop -> {
					System.out.println("\tname: " + prop.getKey() + " (value: " + prop.getValue().getAsString() + ")");
				});
			});
			System.out.println("");
		});
	} 
}
