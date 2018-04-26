package com.audit.dao;

import java.util.List;

import com.audit.model.User;

public interface UserDao {
	
	public User register(User user);
	public User login(User user);
	   public boolean valid(String loginId);
	   public boolean validpass(String pswd);
	  
	   public List<User> assign();
}
