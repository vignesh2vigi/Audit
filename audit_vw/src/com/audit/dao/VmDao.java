package com.audit.dao;

import java.util.List;

import com.audit.model.Vmlogin;

public interface VmDao {
	
	public Vmlogin dealerinfo(Vmlogin vmlogin);

	public Vmlogin details();
	
	public Vmlogin stockup(Vmlogin vmlogin);
	
	public List<Vmlogin> dealerlist();

	public List<Vmlogin> stocklist(int sno);
	
	public Vmlogin assign(Vmlogin vmlogin);
	
	public Vmlogin assigninfo(String audit_id);
	
	public List<Vmlogin> assigndealerlist();
	public List<Vmlogin> completedealerlist();
}
