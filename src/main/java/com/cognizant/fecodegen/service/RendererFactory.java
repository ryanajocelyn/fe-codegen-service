/**
 * 
 */
package com.cognizant.fecodegen.service;

import com.cognizant.fecodegen.bo.JsonDocument;
import com.cognizant.fecodegen.components.render.IRenderer;
import com.cognizant.fecodegen.components.render.RendererPool;
import com.cognizant.fecodegen.exception.CodeGenException;

/**
 * @author 238209
 *
 */
public class RendererFactory {

	public static IRenderer getRenderer(String type, String outFilePath, JsonDocument configJson) 
			throws CodeGenException {
		
		IRenderer renderer = null;
		
		switch (type) {
		case "PackageJson":
			renderer = RendererPool.INSTANCE.getPjRenderer();
			break;
			
		case "React":
			renderer = RendererPool.INSTANCE.getReactComponentRenderer();
			break;
			
		default:
			break;
		}
		
		renderer.setOutFilePath(outFilePath);
		renderer.setConfig(configJson);
		return renderer;
	}
	
}
