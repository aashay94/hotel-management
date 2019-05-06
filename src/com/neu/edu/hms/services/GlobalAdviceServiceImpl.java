package com.neu.edu.hms.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.edu.hms.DAO.ToDoListDAO;
import com.neu.edu.hms.entity.ToDo;


@Service
public class GlobalAdviceServiceImpl implements GlobalAdviceService{

	@Autowired
	private ToDoListDAO toDoDAO;
	
	@Override
	@Transactional
	public List<ToDo> gettoDo() {
		return toDoDAO.getToDoList();
	}
	@Override
	@Transactional
	public void addtoDo(ToDo toDo) {
		toDoDAO.addToDoList(toDo);
		
	}
	

}
