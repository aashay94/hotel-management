package com.neu.edu.hms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.services.CommingCheckoutsService;
/**
 * 
 * @author aasha
 *
 */
@Controller
@RequestMapping("/commingCheckouts")
public class CheckoutsController {

	@Autowired
	private CommingCheckoutsService checkOutService;
	@GetMapping("/list")
	public String checkoutsList(Model theModel) {

		List <Guest> guestCheckouts = checkOutService.getCheckouts();
		
		theModel.addAttribute("guestList", guestCheckouts);
		return "guestList";

	}

}

