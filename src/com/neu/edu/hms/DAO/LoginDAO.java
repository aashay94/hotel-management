package com.neu.edu.hms.DAO;
import java.util.List;

import com.neu.edu.hms.entity.User;

public interface LoginDAO {
	public User getUser(String login);
	public void saveOrUpdateUser(User user);
	public void deleteUser(int id);
	public List<User> getAllUsers();
	public User getUser(int id);
}
