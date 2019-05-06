package com.neu.edu.hms.services;

import java.util.List;

import com.neu.edu.hms.entity.User;

public interface AdministrationService {
	//getting the user list
	public List<User> getUsers();
	//deleting users
	public void deleteUser(int id);
	//registering users
	public void registerUser(User user);
	//getting user based on id
	public User getUser(int id);
}
