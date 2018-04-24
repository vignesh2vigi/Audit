package com.audit.dao;

import com.audit.model.User;

public interface UserDao {
	
	public User register(User user);
	public User login(User user);
	   public boolean valid(String loginId);
	   public boolean validpass(String user_password);
	   public User insert(User user);
}