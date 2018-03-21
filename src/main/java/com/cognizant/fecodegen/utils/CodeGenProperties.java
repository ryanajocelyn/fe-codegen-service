/**
 * 
 */
package com.cognizant.fecodegen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.cglib.core.CodeGenerationException;

/**
 * @author 238209
 *
 */
public class CodeGenProperties {

	private static Logger logger = Logger.getLogger(CodeGenProperties.class);
	
	private Properties properties;
	
	private static CodeGenProperties INSTANCE;
	
	public CodeGenProperties() {
		properties = new Properties();

		try {
			InputStream inStream = 
					getClass().getClassLoader()
								.getResourceAsStream("application.properties");
			
			properties.load(inStream);
		} catch (IOException e) {
			logger.error("Error while loading properties file.", e);
			throw new CodeGenerationException(e);
		}
	}
	
	public static CodeGenProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CodeGenProperties();
		}
		
		return INSTANCE;
	}
	
	public static String getValue (String key) {
		return getInstance().getProperties().getProperty(key);
	}

	public static Properties getProps () {
		return getInstance().getProperties();
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
}
