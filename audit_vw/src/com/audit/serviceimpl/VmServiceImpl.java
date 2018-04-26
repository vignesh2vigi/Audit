package com.audit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.dao.VmDao;
import com.audit.model.Vmlogin;
import com.audit.service.VmService;

public class VmServiceImpl implements VmService {
	@Autowired
	VmDao vmDao;
	
	@Override
	public Vmlogin dealerinfo(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		return vmDao.dealerinfo(vmlogin);
	}

	@Override
	public Vmlogin details() {
		// TODO Auto-generated method stub
		return vmDao.details();
	}

	@Override
	public Vmlogin stockup(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		return vmDao.stockup(vmlogin);
	}

	

	@Override
	public List<Vmlogin> stocklist(int sno) {
		// TODO Auto-generated method stub
		return vmDao.stocklist(sno);
	}

	@Override
	public List<Vmlogin> dealerlist() {
		// TODO Auto-generated method stub
		return vmDao.dealerlist();
	}

	@Override
	public Vmlogin assign(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		return vmDao.assign(vmlogin);
	}

	@Override
	public Vmlogin assigninfo(String audit_id) {
		// TODO Auto-generated method stub
		return vmDao.assigninfo(audit_id);
	}

	@Override
	public List<Vmlogin> assigndealerlist() {
		// TODO Auto-generated method stub
		return vmDao.assigndealerlist();
	}

	@Override
	public List<Vmlogin> completedealerlist() {
		// TODO Auto-generated method stub
		return vmDao.completedealerlist();
	}


}
