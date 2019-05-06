package com.neu.edu.hms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.edu.hms.entity.User;
import com.neu.edu.hms.services.LoginService;

/**
 * Login controller class
 * @author aasha
 *
 */

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@GetMapping("/login")
	public String showLogin() {
		//function to show the login form
		return "login";
	}
	
	@PostMapping("/login")
	public String verifyLoginCredentials(@RequestParam String login, @RequestParam String password,
			HttpSession session,Model theModel){
		User user = loginService.loggedInUser(login, password);
		if(user==null) {
			theModel.addAttribute("loginError", "Not able to login. Try again....!!!!");
			return "login";
		}
		session.setAttribute("loggedInUser", user);
		return "redirect:/hotelStatus/"; // redirecting to the hotel status page
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "redirect:/";
	}
}
