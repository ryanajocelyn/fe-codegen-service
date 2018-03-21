/**
 * 
 */
package com.cognizant.fecodegen.bo;

import java.io.Serializable;

/**
 * @author 238209
 *
 */
public class CodeGenRequest implements Serializable {

	/**
	 * Serial Version Number
	 */
	private static final long serialVersionUID = 6889270458578142486L;

	private String uiTechnology;
	
	private String uiLayout;
	
	private String codeGenPath;
	
	private String configName;

	/**
	 * @return the uiTechnology
	 */
	public String getUiTechnology() {
		return uiTechnology;
	}

	/**
	 * @param uiTechnology the uiTechnology to set
	 */
	public void setUiTechnology(String uiTechnology) {
		this.uiTechnology = uiTechnology;
	}

	/**
	 * @return the uiLayout
	 */
	public String getUiLayout() {
		return uiLayout;
	}

	/**
	 * @param uiLayout the uiLayout to set
	 */
	public void setUiLayout(String uiLayout) {
		this.uiLayout = uiLayout;
	}

	/**
	 * @return the codeGenPath
	 */
	public String getCodeGenPath() {
		return codeGenPath;
	}

	/**
	 * @param codeGenPath the codeGenPath to set
	 */
	public void setCodeGenPath(String codeGenPath) {
		this.codeGenPath = codeGenPath;
	}

	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * @param configName the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}
}
