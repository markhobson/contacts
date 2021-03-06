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
package org.hobsoft.contacts.server.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller for authentication.
 */
@Controller
public class AuthenticationController
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(path = "/login", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView loginForm(@RequestParam(required = false) String logout,
		@RequestParam(required = false) String error)
	{
		Map<String, Object> model = new HashMap<>();
		
		if (logout != null)
		{
			model.put("logout", "logout");
		}
		
		if (error != null)
		{
			model.put("error", "error");
		}
		
		return new ModelAndView("auth/login", model);
	}
}
