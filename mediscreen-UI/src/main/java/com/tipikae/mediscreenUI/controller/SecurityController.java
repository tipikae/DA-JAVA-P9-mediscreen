/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Security controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
public class SecurityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
	
	@GetMapping("/login")
	public String getloginForm() {
		return "security/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/login";
	}
}
