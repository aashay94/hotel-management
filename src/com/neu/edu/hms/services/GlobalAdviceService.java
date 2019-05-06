package com.neu.edu.hms.services;

import java.util.List;

import com.neu.edu.hms.entity.ToDo;

public interface GlobalAdviceService {
	public List<ToDo> gettoDo();
	public void addtoDo(ToDo toDo);	
}
