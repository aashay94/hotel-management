package com.neu.edu.hms.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.edu.hms.DAO.LoginDAO;
import com.neu.edu.hms.entity.User;

@Service
public class AdministrationServiceImpl implements AdministrationService{

	@Autowired
	private LoginDAO loginDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return loginDAO.getAllUsers();
	}
	
	@Override
	@Transactional
	public void deleteUser(int id) {
		loginDAO.deleteUser(id);
		
	}
	
	@Override
	@Transactional
	public void registerUser(User user) {
		loginDAO.saveOrUpdateUser(user);
		
	}

	@Override
	@Transactional
	public User getUser(int id) {
		return loginDAO.getUser(id);
	}

}
