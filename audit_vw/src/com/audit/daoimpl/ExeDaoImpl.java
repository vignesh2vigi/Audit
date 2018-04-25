package com.audit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.audit.dao.ExeDao;
import com.audit.model.Exe;
import com.audit.utility.Password;

public class ExeDaoImpl implements ExeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Override
	public Exe login(Exe exe) {
		// TODO Auto-generated method stub
		Exe user2 = new Exe();
		try {
			Password td= new Password();
			
			  String pass=td.encrypt(exe.getPswd());
				System.out.println(""+pass);
			
			List<Exe> audit_vw = new ArrayList<Exe>(); 
			
			String query = "SELECT sno,first_name FROM login_info WHERE loginId='"+exe.getLoginId()+"' AND pswd='"+pass+"' AND active_status='1' "; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
			if (audit_vw.size() > 0) {
				user2.setStatus(true);
					user2.setSno(audit_vw.get(0).getSno());
					user2.setFirst_name(audit_vw.get(0).getFirst_name());
				/*userOutObj.setLoginfo(user2);*/
				} else { 
					
					user2.setStatus(false);
					/*userOutObj.setLoginfo(user2);*/
					}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user2.setMessage(e.getMessage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user2.setMessage(e.getMessage());
		}
	
		return user2;
	}

	@Override
	public boolean valid(String loginId) {
		// TODO Auto-generated method stub
		Exe bank = new Exe();
		List<Exe> bankModelObjArray = new ArrayList<Exe>(); 
		String query = "SELECT loginId FROM login_info WHERE loginId='"+loginId+"'"; 
	                                        
		bankModelObjArray = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
		if (bankModelObjArray.size() > 0) {
			bank.setStatus(true);
			bank.setLoginId(bankModelObjArray.get(0).getLoginId());
			
		
		return true;
		
			} else { 
				
				bank.setStatus(false);
				return false;
				}
	}

	@Override
	public boolean validpass(String pswd) {
		// TODO Auto-generated method stub
		try {
			Exe bank = new Exe();
			
			 Password td=new Password();
			 
				  String pass=td.encrypt(pswd);
					System.out.println("valid"+pass);
			
				List<Exe> bankModelObjArray = new ArrayList<Exe>(); 
				String query = "SELECT pswd FROM login_info WHERE pswd='"+pass+"'"; 
				
				bankModelObjArray = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
				if (bankModelObjArray.size() > 0) {
					bank.setStatus(true);
					bank.setPswd(bankModelObjArray.get(0).getPswd());
				
			
				return true;
				
					} else { 
						
						bank.setStatus(false);
						return false;
						}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Exe insert(Exe exe) {
		// TODO Auto-generated method stub
		Exe vm = new Exe();
		try {
			
			int insertDealerReg_int = 0;
			
			String insertDealerReg_query = "INSERT INTO dealer_visit_info (audit_id,reached_dt,ctp_meet_dt,start_dt,ctp_name,no_stock) VALUES (?,?,?,?,?,?)";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
							exe.getAudit_id(),exe.getReached_dt(),exe.getCtp_meet_dt(),exe.getStart_dt(),exe.getCtp_name(),exe.getNo_stock()	
						
							});
			if (insertDealerReg_int > 0) {
				vm.setStatus(true);
				
			} else {
				vm.setStatus(false);
				
			}
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		return vm;
	}
/*	@Override
	public List<Exe> auditlist(String audit_id) {
		// TODO Auto-generated method stub
		Exe custVehiDetailsOutObj = new Exe();
        List<Exe> custDetailsList = new ArrayList<Exe>();
		String pendingQuery = "SELECT audit_id,vin_no,reg_no FROM stock_info WHERE audit_id='"+audit_id+"'";
		System.out.println("pendingQuery------------------------------------->"+pendingQuery);
		try {
			
			custDetailsList = this.jdbcTemplate.query(pendingQuery,
					new BeanPropertyRowMapper(Exe.class));
			
		} catch (Exception e) {
			custVehiDetailsOutObj.setStatus(false);
			
		}
		
		return custDetailsList;
	}*/
	@Override
	public Exe stockinsert(Exe exe) {
		// TODO Auto-generated method stub
		Exe vm = new Exe();
		Exe user2 =new Exe();
	try {
			
		
			List<Exe> audit_vw = new ArrayList<Exe>(); 
			
			String query = "SELECT  sno FROM dealer_info where audit_id='"+exe.getAudit_id()+"'"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
			if (audit_vw.size() > 0) {
				user2.setStatus(true);
				user2.setSno(audit_vw.get(0).getSno());
				
				
				/*userOutObj.setLoginfo(user2);*/
				} else { 
					
					user2.setStatus(false);
					/*userOutObj.setLoginfo(user2);*/
					}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(""+user2.getSno());
		
		
		
		try {
			
			int insertDealerReg_int = 0;
			
			String insertDealerReg_query = "INSERT INTO stock_verify_info (dealer_mapped,audit_id,vin_no,veh_avail_status,vehi_images,submit_proof,comments,verify_dt) VALUES (?,?,?,?,?,?,?,now())";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
							user2.getSno(),exe.getAudit_id(),exe.getVin_no(),exe.getVeh_avail_status(),exe.getVehi_images(),exe.getSubmit_proof(),exe.getComments()

						
							});
			if (insertDealerReg_int > 0) {
				vm.setStatus(true);
				
			} else {
				vm.setStatus(false);
				
			}
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		
		
		
		try {
	
			int insertDealerReg_int = 0;
			
			String insertDealerReg_query ="UPDATE stock_info SET stock_status='1' WHERE vin_no=? AND audit_id=? ";
System.out.println("Image Insert=========="+insertDealerReg_query);
			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] {exe.getVin_no(),exe.getAudit_id()});
			if (insertDealerReg_int > 0) {
				vm.setStatus(true);
				
			} else {
				vm.setStatus(false);
			
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			vm.setStatus(false);
			
		}
		
		
		return vm;
	}
	@Override
	public Exe deallist(int sno) {
		// TODO Auto-generated method stub
		Exe last = new Exe();
		try {
			
			
			List<Exe> audit_vw = new ArrayList<Exe>(); 
			
			String query = "SELECT di.dealer_name,di.audit_id,di.address,di.appt_dt,di.no_stock,di.ctp_name FROM dealer_info as di JOIN assigner_info as ai WHERE assign_to='"+sno+"' AND audit_status='2' ORDER BY appt_dt DESC"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
			if (audit_vw.size() > 0) {
				Exe user2 = new Exe();
				user2.setStatus(true);
					user2.setSno(audit_vw.get(0).getSno());
					user2.setDealer_name(audit_vw.get(0).getDealer_name());
					user2.setAudit_id(audit_vw.get(0).getAudit_id());
					user2.setAddress(audit_vw.get(0).getAddress());
					user2.setAppt_dt(audit_vw.get(0).getAppt_dt());
					user2.setNo_stock(audit_vw.get(0).getNo_stock());
					user2.setCtp_name(audit_vw.get(0).getCtp_name());
					last.setDealerlist(audit_vw);
				/*userOutObj.setLoginfo(user2);*/
				} else { 
					
					last.setStatus(false);
					/*userOutObj.setLoginfo(user2);*/
					}
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			last.setMessage(e.getMessage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			last.setMessage(e.getMessage());
		}
		
	
		return last;
	}
	@Override
	public Exe auditlist(String audit_id) {
		// TODO Auto-generated method stub
		Exe last = new Exe();
		try {
			
			
			List<Exe> audit_vw = new ArrayList<Exe>(); 
			
			String query = "SELECT audit_id,vin_no,reg_no FROM stock_info WHERE audit_id='"+audit_id+"'"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Exe.class)); 
			if (audit_vw.size() > 0) {
				Exe user2 = new Exe();
				user2.setStatus(true);
					
					user2.setAudit_id(audit_vw.get(0).getAudit_id());
					user2.setVin_no(audit_vw.get(0).getVin_no());
					user2.setReg_no(audit_vw.get(0).getReg_no());
					last.setAuditlist(audit_vw);
				/*userOutObj.setLoginfo(user2);*/
				} else { 
					
					last.setStatus(false);
					/*userOutObj.setLoginfo(user2);*/
					}
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			last.setMessage(e.getMessage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			last.setMessage(e.getMessage());
		}
		
	
		return last;
	}
	

}
