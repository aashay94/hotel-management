package com.neu.edu.hms.DAO;

import java.util.List;

import com.neu.edu.hms.entity.ToDo;

public interface ToDoListDAO {

	public void addToDoList(ToDo toDo);
	public List<ToDo> getToDoList();
	public void deleteToDoList(ToDo toDo);
	public void deleteToDo(int id);

}
