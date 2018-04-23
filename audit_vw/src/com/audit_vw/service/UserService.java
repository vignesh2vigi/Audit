package com.audit_vw.service;

import com.audit_vw.model.User;



public interface UserService {
	
	public User register(User user);
	public User login(User user);
	 public boolean valid(String loginId);
	   public boolean validpass(String user_password);
	   public User insert(User user);
}
