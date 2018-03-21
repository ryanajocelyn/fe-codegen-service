/**
 * 
 */
package com.cognizant.fecodegen.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.fecodegen.CodeGenTemplateParser;
import com.cognizant.fecodegen.bo.CodeGenRequest;
import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.cognizant.fecodegen.utils.JsonUtils;

/**
 * @author 238209
 *
 */
public class CodeGenerator implements ICodeGenerator {

	protected static Logger LOGGER = Logger.getLogger(CodeGenerator.class);
	
	protected CodeGenRequest request;
	
	@Autowired
	protected CodeGenTemplateParser parser;
	
	protected JsonDocument configJson;

	private void configure() throws CodeGenException {
		if (request != null) {
			String configName = request.getConfigName();
			String configString = parser.read("config/"+configName+".json");

			configString = configString.replace("${layout_json}", request.getUiLayout());
			configJson = JsonUtils.getJsonObject(configString);
		}
	}

	@Override
	public boolean generate() throws CodeGenException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the request
	 */
	public CodeGenRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 * @throws CodeGenException 
	 */
	public void setRequest(CodeGenRequest request) throws CodeGenException {
		this.request = request;
		
		configure();
	}

}
