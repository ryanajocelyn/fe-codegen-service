/**
 * 
 */
package com.cognizant.fecodegen.components.render;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 238209
 *
 */
public class RendererPool {

	public static RendererPool INSTANCE;
	
	public static RendererPool getInstance() {
		if (INSTANCE == null) {
			 INSTANCE = new RendererPool();
		}
		
		return INSTANCE;
	}
	
	@Autowired
	private PackageJsonRenderer pjRenderer;
	
	@Autowired
	private ReactComponentRenderer reactComponentRenderer;

	/**
	 * @return the pjRenderer
	 */
	public PackageJsonRenderer getPjRenderer() {
		return pjRenderer;
	}

	/**
	 * @param pjRenderer the pjRenderer to set
	 */
	public void setPjRenderer(PackageJsonRenderer pjRenderer) {
		this.pjRenderer = pjRenderer;
	}

	/**
	 * @return the reactComponentRenderer
	 */
	public ReactComponentRenderer getReactComponentRenderer() {
		return reactComponentRenderer;
	}

	/**
	 * @param reactComponentRenderer the reactComponentRenderer to set
	 */
	public void setReactComponentRenderer(ReactComponentRenderer reactComponentRenderer) {
		this.reactComponentRenderer = reactComponentRenderer;
	}

}
