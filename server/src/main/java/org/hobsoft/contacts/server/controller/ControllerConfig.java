/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.contacts.server.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.mustache.MustacheTemplateFactory;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;
import org.springframework.web.servlet.view.mustache.java.MustacheJTemplateFactory;

/**
 * Spring configuration for controllers.
 */
@Configuration
@ComponentScan
public class ControllerConfig extends WebMvcConfigurationSupport
{
	// ----------------------------------------------------------------------------------------------------------------
	// WebMvcConfigurationSupport methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Bean
	public MustacheTemplateFactory mustacheTemplateFactory()
	{
		return new MustacheJTemplateFactory();
	}
	
	@Bean
	public ViewResolver mustacheViewResolver()
	{
		MustacheViewResolver viewResolver = new MustacheViewResolver();
		viewResolver.setTemplateFactory(mustacheTemplateFactory());
		viewResolver.setPrefix("classpath:/view/");
		viewResolver.setSuffix(".mustache");
		return viewResolver;
	}
}
