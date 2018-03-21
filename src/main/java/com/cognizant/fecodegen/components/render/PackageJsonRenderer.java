/**
 * 
 */
package com.cognizant.fecodegen.components.render;

import java.util.Properties;

/**
 * @author 238209
 *
 */
public class PackageJsonRenderer extends BaseRenderer {

	public static String PREFIX = "codegen.packagejson"; 
	
	public PackageJsonRenderer(Properties properties) {
		super(PREFIX, properties);
	}

}
