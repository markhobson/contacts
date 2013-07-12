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
package org.hobsoft.contacts.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	// WebSecurityConfigurerAdapter methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring()
			.antMatchers("/favicon.ico");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
			.withUser("mark").password("password").roles("USER");
	}
}