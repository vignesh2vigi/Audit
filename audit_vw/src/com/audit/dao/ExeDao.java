package com.audit.dao;

import com.audit.model.Exe;

public interface ExeDao {
	
	   public Exe login(Exe exe);
	   public boolean valid(String loginId);
	   public boolean validpass(String pswd);
	   public Exe deallist(int sno);
	   public Exe insert(Exe exe);
	   public Exe auditlist(String audit_id);
	   public Exe stockinsert(Exe exe);
	   public Exe update(String audit_id);
	
}
