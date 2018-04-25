package com.audit.service;

import com.audit.model.Exe;

public interface ExeService {
	   public Exe login(Exe exe);
	   public boolean valid(String loginId);
	   public boolean validpass(String user_password);
	 
	   public Exe deallist(int sno);
	   public Exe insert(Exe exe);
	   /*public List<Exe> auditlist(String audit_id);*/
	   public Exe auditlist(String audit_id);
	   public Exe stockinsert(Exe exe);
}
