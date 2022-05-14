/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/patient/all";
	}
}
