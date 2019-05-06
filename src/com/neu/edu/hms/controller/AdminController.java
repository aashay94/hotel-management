package com.neu.edu.hms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.edu.hms.entity.User;
import com.neu.edu.hms.services.AdministrationService;
/**
 * 
 * @author aasha
 *
 */
@Controller
@RequestMapping("/administration")
public class AdminController {

	@Autowired
	private AdministrationService adminService;
	@GetMapping("/")
	public String checkAdminAndShow(HttpSession session, Model theModel) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if(loggedInUser != null && loggedInUser.getFirstName().equals("admin")) {

			List<User> userList = adminService.getUsers();
			theModel.addAttribute("userList", userList);
			return "userList";

		}else {

			theModel.addAttribute("noAdminLoggedIn","Please log in as an admin to use administration tab");
			return "hotelStatus";

		}
	}
	
	@GetMapping("/registerUser")
	public String showRegisterForm(Model theModel) {
		User user = new User();
		theModel.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/registerUser")
	public String registerNewUser(@Valid @ModelAttribute("user") User newUser, BindingResult bindingResult, Model theModel) {
		if(bindingResult.hasErrors()) {

			theModel.addAttribute("user", newUser);
			return "register";

		}else {
			adminService.registerUser(newUser);
			return "redirect:/administration/";
		}
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("tempUserId") int id) {
		adminService.deleteUser(id);
		return "redirect:/administration/";
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("tempUserId") int id, Model theModel) {
		User user = adminService.getUser(id);
		theModel.addAttribute("user", user);
		return "register";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
