package com.neu.edu.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.neu.edu.hms.entity.ToDo;
import com.neu.edu.hms.services.GlobalAdviceService;
/**
 * @author aasha
 */
@ControllerAdvice
public class GlobalController {

	@Autowired
	GlobalAdviceService adviceService;

	@ModelAttribute
	public void addToDoListModel(Model model) {

		List<ToDo> toDo = adviceService.gettoDo();
				model.addAttribute("toDo", toDo);

	}
}
