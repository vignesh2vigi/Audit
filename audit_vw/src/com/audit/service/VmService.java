package com.audit.service;

import java.util.List;

import com.audit.model.Vmlogin;

public interface VmService {

	public Vmlogin dealerinfo(Vmlogin vmlogin);
	public Vmlogin details();
	public Vmlogin stockup(Vmlogin vmlogin);
	public List<Vmlogin> stocklist(int sno);
	public List<Vmlogin> dealerlist();
	public Vmlogin assign(Vmlogin vmlogin);
	public Vmlogin assigninfo(String audit_id);
	public List<Vmlogin> assigndealerlist();
	public List<Vmlogin> completedealerlist();
}
