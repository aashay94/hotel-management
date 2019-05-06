package com.neu.edu.hms.DAO;
/**
 * The class models a Data Access object for Users entities stored in the Database
 */

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.edu.hms.entity.User;

@Repository
public class UserDAOImpl implements LoginDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public User getUser(String login) {
		Session session = sessionFactory.getCurrentSession();
		User theUser = null;
		Query q = session.createQuery("select u from User u where u.login =:login", User.class);
		q.setParameter("login", login);
		theUser = (User) q.getSingleResult();
		return theUser;
	}
	
	@Override
	public void saveOrUpdateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	
	@Override
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from User where id=:userId");
		q.setParameter("userId", id);	
		q.executeUpdate();
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from User", User.class);
		List<User> userList = q.getResultList();
		return userList;
	}

	@Override
	public User getUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		return user;

	}

}
