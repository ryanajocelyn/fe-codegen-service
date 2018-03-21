/**
 * 
 */
package com.cognizant.fecodegen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cognizant.fecodegen.exception.CodeGenException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author 238209
 *
 */
public class CodeGenTemplateParser {

	private static Logger LOGGER = Logger.getLogger(CodeGenTemplateParser.class);
	
	@Autowired
	protected Configuration fmConfig;

	public String parse(String templateName, Map<String, String> contextVariables) 
			throws CodeGenException {
		String response = null;

		try {
			
			Template t = fmConfig.getTemplate(templateName);
			response = FreeMarkerTemplateUtils.processTemplateIntoString(t, contextVariables);
		} catch (Exception e) {
			LOGGER.error("Error while parsing the template: " + templateName, e);
			throw new CodeGenException("Template Parser Exception: " + templateName, e);
		}

		return response;
	}

	public String read(String templatePath) throws CodeGenException {
		InputStream is = 
				getClass().getClassLoader()
							.getResourceAsStream(templatePath);
		
		String configString = null;
		try {
			byte[] configByteArray = new byte[is.available()];
			IOUtils.read(is, configByteArray);
			
			configString = new String(configByteArray);
		} catch (IOException e) {
			LOGGER.error("Error while reading the configuration: " + templatePath, e);
			throw new CodeGenException(e);
		}

		return configString;
	}
}
