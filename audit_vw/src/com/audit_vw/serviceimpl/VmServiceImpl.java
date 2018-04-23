package com.audit_vw.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit_vw.dao.VmDao;
import com.audit_vw.model.Vmlogin;
import com.audit_vw.service.VmService;

public class VmServiceImpl implements VmService {
	@Autowired
	VmDao vmDao;
	
	@Override
	public Vmlogin dealerinfo(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		return vmDao.dealerinfo(vmlogin);
	}

}
