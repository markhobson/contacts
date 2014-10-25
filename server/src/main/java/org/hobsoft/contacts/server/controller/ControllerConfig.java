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

import org.hobsoft.contacts.server.support.spring.mustache.MustacheConfig;
import org.hobsoft.contacts.server.support.spring.mustache.MustacheConfigurer;
import org.hobsoft.contacts.server.support.spring.mustache.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/login").setViewName("auth/login");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Bean
	public ViewResolver jspViewResolver()
	{
		UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jspx");
		return viewResolver;
	}
	
	@Bean
	public MustacheConfig mustacheConfig()
	{
		return new MustacheConfigurer();
	}
	
	@Bean
	public ViewResolver mustacheViewResolver()
	{
		UrlBasedViewResolver viewResolver = new MustacheViewResolver();
		viewResolver.setPrefix("/view/");
		viewResolver.setSuffix(".mustache");
		viewResolver.setViewNames("contact/contactDelete");
		viewResolver.setOrder(1);
		return viewResolver;
	}
}
