/**
 * 
 */
package com.cognizant.fecodegen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cognizant.fecodegen.CodeGenTemplateParser;
import com.cognizant.fecodegen.components.GeneratorPool;
import com.cognizant.fecodegen.components.ReactJsGenerator;
import com.cognizant.fecodegen.components.render.PackageJsonRenderer;
import com.cognizant.fecodegen.components.render.ReactComponentRenderer;
import com.cognizant.fecodegen.components.render.RendererPool;
import com.cognizant.fecodegen.utils.CodeGenProperties;

/**
 * @author 238209
 *
 */
@Configuration
public class ComponentConfiguration {

	@Bean
	public GeneratorPool generatorPool() {
		return GeneratorPool.getInstance();
	}
	
	@Bean
	public RendererPool rendererPool() {
		return RendererPool.getInstance();
	}

	@Bean
	public ReactJsGenerator reactJsGenerator() {
		return new ReactJsGenerator();
	}

	@Bean
	public CodeGenTemplateParser codeGenTemplateParser() {
		return new CodeGenTemplateParser();
	}
	
	@Bean
	public PackageJsonRenderer packageJsonRenderer() {
		return new PackageJsonRenderer(CodeGenProperties.getProps());
	}
	
	@Bean
	public ReactComponentRenderer reactComponentRenderer() {
		return new ReactComponentRenderer(CodeGenProperties.getProps());
	}
}
