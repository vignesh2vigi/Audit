package com.audit_vw.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit_vw.dao.UserDao;
import com.audit_vw.model.User;
import com.audit_vw.service.UserService;

public class UserServiceImpl implements UserService {

	
	@Autowired
	UserDao userDao;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	

	@Override
	public boolean valid(String user_id) {
		// TODO Auto-generated method stub
		return userDao.valid(user_id);
	}



	@Override
	public boolean validpass(String password) {
		// TODO Auto-generated method stub
		return userDao.validpass(password);
	}



	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		return userDao.register(user);
	}



	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	
}
