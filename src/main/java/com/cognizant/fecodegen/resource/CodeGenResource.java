/**
 * 
 */
package com.cognizant.fecodegen.resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.fecodegen.bo.CodeGenRequest;
import com.cognizant.fecodegen.components.CodeGenerator;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.cognizant.fecodegen.service.CodeGenFactory;

/**
 * Resource Class to generate the UI source code
 * 
 * @author 238209
 *
 */
@RestController
public class CodeGenResource {

	private static Logger LOGGER = Logger.getLogger(CodeGenResource.class);
	
	@PostMapping("/process/ui")
	public @ResponseBody String processUi(@RequestBody CodeGenRequest request) {
		String response = "Success !!";
		
		try {
			CodeGenerator generator = CodeGenFactory.getGenerator(request);
			generator.generate();
		} catch (CodeGenException e) {
			response = "Error !!";
			LOGGER.error("Error while processing the UI.", e);
		}
		
		return response;
	}
	
}
