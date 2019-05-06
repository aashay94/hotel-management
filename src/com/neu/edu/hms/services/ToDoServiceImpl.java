package com.neu.edu.hms.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.neu.edu.hms.DAO.ToDoListDAO;
import com.neu.edu.hms.entity.ToDo;


@Service
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoListDAO toDoDAO;
	
	@Override
	@Transactional
	public void deleteToDo(ToDo theToDo) {
		toDoDAO.deleteToDoList(theToDo);
	}

	@Override
	@Transactional
	public void addToDo(ToDo theToDo) {
		toDoDAO.addToDoList(theToDo);
		
	}
	
	@Override
	@Transactional
	public void deleteToDo(int id) {
		toDoDAO.deleteToDo(id);
		
	}
	
	

}
