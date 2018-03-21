/**
 * 
 */
package com.cognizant.fecodegen.components;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 238209
 *
 */
public class GeneratorPool {

	public static GeneratorPool INSTANCE;
	
	public static GeneratorPool getInstance() {
		if (INSTANCE == null) {
			 INSTANCE = new GeneratorPool();
		}
		
		return INSTANCE;
	}
	
	@Autowired
	private ReactJsGenerator reactJsGenerator;

	/**
	 * @return the reactJsGenerator
	 */
	public ReactJsGenerator getReactJsGenerator() {
		return reactJsGenerator;
	}

	/**
	 * @param reactJsGenerator the reactJsGenerator to set
	 */
	public void setReactJsGenerator(ReactJsGenerator reactJsGenerator) {
		this.reactJsGenerator = reactJsGenerator;
	}
}
