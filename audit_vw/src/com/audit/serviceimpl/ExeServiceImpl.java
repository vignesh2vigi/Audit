package com.audit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.dao.ExeDao;
import com.audit.model.Exe;
import com.audit.model.Vmlogin;
import com.audit.service.ExeService;



public class ExeServiceImpl implements ExeService {
	@Autowired
	ExeDao exeDao;

	@Override
	public Exe login(Exe exe) {
		// TODO Auto-generated method stub
		return exeDao.login(exe);
	}

	@Override
	public boolean valid(String loginId) {
		// TODO Auto-generated method stub
		return exeDao.valid(loginId);
	}

	@Override
	public boolean validpass(String user_password) {
		// TODO Auto-generated method stub
		return exeDao.validpass(user_password);
	}

	

	@Override
	public Exe insert(Exe exe) {
		// TODO Auto-generated method stub
		return exeDao.insert(exe);
	}

	

	@Override
	public Exe stockinsert(Exe exe) {
		// TODO Auto-generated method stub
		return exeDao.stockinsert(exe);
	}

	@Override
	public Exe deallist(int sno) {
		// TODO Auto-generated method stub
		return exeDao.deallist(sno);
	}

	@Override
	public Exe auditlist(String audit_id) {
		// TODO Auto-generated method stub
		return exeDao.auditlist(audit_id);
	}

	@Override
	public Exe update(String audit_id) {
		// TODO Auto-generated method stub
		return exeDao.update(audit_id);
	}

	

	
}
