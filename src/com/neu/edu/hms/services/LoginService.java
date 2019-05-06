package com.neu.edu.hms.services;

import com.neu.edu.hms.entity.User;

public interface LoginService {
	public User getUser (String login);
	
	public User loggedInUser(String login, String password);
}
