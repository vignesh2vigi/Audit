package com.audit_vw.dao;

import com.audit_vw.model.User;

public interface UserDao {
	
	public User register(User user);
	public User login(User user);
	   public boolean valid(String loginId);
	   public boolean validpass(String user_password);
	   public User insert(User user);
}
