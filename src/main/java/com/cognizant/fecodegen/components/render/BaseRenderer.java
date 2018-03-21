/**
 * 
 */
package com.cognizant.fecodegen.components.render;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.fecodegen.CodeGenTemplateParser;
import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.exception.CodeGenException;
import com.cognizant.fecodegen.utils.Constants;

/**
 * @author 238209
 *
 */
public class BaseRenderer implements IRenderer {
	
	private static Logger LOGGER = Logger.getLogger(BaseRenderer.class);
	
	protected String prefix;
	protected String templateName;
	protected String outFilename;
	protected String outFilePath;
	protected String relativePath;
	
	protected Properties properties;
	
	protected Map<String, String> subParams;
	
	protected JsonDocument config;
	
	@Autowired
	protected CodeGenTemplateParser parser;

	public BaseRenderer(String prefix, Properties properties) {
		this.prefix = prefix;
		this.properties = properties;
		
		subParams = new HashMap<String, String>();
	}

	public void initializeParams(JsonDocument jsonDoc) {
		this.templateName = jsonDoc.getAsString(Constants.TEMPLATE_NAME);
		this.outFilename = jsonDoc.getAsString(Constants.OUTPUT_FILE_NAME);
		this.relativePath = jsonDoc.getAsString(Constants.RELATIVE_PATH);
	}

	@Override
	public boolean render(JsonDocument jsonDoc) throws CodeGenException {
		String fileContent = readTemplate(jsonDoc);
		
		boolean response = false;
		if (StringUtils.isNotBlank(fileContent)) {
			
			if (validate()) {
				response = write(fileContent);;
			}
		}

		return response;
	}

	public String readTemplate(JsonDocument jsonDoc) throws CodeGenException {
		subParams = jsonDoc.getAsMap();
		
		return parser.parse(templateName, subParams);
	}
	
	public boolean validate() {
		return true;
	}
	
	public boolean write(String fileContent) throws CodeGenException {
		String fullFilePath = outFilePath;
		if (StringUtils.endsWith(outFilePath, Constants.FORWARD_SLASH) == false) {
			fullFilePath = fullFilePath + "/";
		}
		
		if (StringUtils.equalsIgnoreCase(relativePath, Constants.FORWARD_SLASH) == false) {
			fullFilePath = fullFilePath + relativePath;
			
			if (StringUtils.endsWith(outFilePath, Constants.FORWARD_SLASH) == false) {
				fullFilePath = fullFilePath + "/";
			}
		}
		
		fullFilePath = fullFilePath + outFilename;
		
		boolean response = false;
		try {
			OutputStream os = new FileOutputStream(new File(fullFilePath));
			IOUtils.write(fileContent, os);
			
			IOUtils.closeQuietly(os);
			response = true;
		} catch (FileNotFoundException e) {
			LOGGER.error("File not Found. Path=" + fullFilePath, e);
			throw new CodeGenException(e);
		} catch (IOException e) {
			LOGGER.error("IO Exception. Path=" + fullFilePath, e);
			throw new CodeGenException(e);
		}
		
		return response;
	}
	
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the outFilename
	 */
	public String getOutFilename() {
		return outFilename;
	}

	/**
	 * @param outFilename the outFilename to set
	 */
	public void setOutFilename(String outFilename) {
		this.outFilename = outFilename;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the outFilePath
	 */
	public String getOutFilePath() {
		return outFilePath;
	}

	/**
	 * @param outFilePath the outFilePath to set
	 */
	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * @return the config
	 */
	public JsonDocument getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(JsonDocument config) {
		this.config = config;
	}
}
