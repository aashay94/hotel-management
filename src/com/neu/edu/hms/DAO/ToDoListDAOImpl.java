package com.neu.edu.hms.DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.edu.hms.entity.ToDo;

@Repository
public class ToDoListDAOImpl implements ToDoListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addToDoList(ToDo toDo) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(toDo);

	}

	@Override
	public List<ToDo> getToDoList() {

		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ToDo");
		List <ToDo> toDoList = q.getResultList();
		return toDoList;
	}

	@Override
	public void deleteToDoList(ToDo toDo) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(toDo);

	}

	@Override
	public void deleteToDo(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from ToDo where id=:toDoId");
		q.setParameter("toDoId", id);	
		q.executeUpdate();

	}

}