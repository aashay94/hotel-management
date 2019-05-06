package com.neu.edu.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author aasha
 */

@Entity
@Table(name = "toDo")
public class ToDo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "task")
	private String task;
	
	public ToDo() {
		
	}

	public ToDo(String task) {
		this.task = task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public String toString() {
		return "[ToDo: id = " + id + " ,task = " + task +" ]";
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    ToDo todo = (ToDo) o;
	    return todo.getTask().equals(this.task) && 
	    		todo.getId() == this.id ;
	}
}
