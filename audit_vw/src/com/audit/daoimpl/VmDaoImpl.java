package com.audit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.audit.dao.VmDao;
import com.audit.model.Vmlogin;
import com.audit.utility.GenerateUUID;

public class VmDaoImpl implements VmDao {

	

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public Vmlogin dealerinfo(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Vmlogin vm = new Vmlogin();
		try {
			
			String requestId = "VM" + GenerateUUID.generateDelearListingId();
			
			int insertDealerReg_int = 0;
			
			
			
			String insertDealerReg_query = "INSERT INTO dealer_info (audit_id,dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,no_stock,auth_letter,audit_status,visibility,insert_dt) VALUES (?,?,?,?,?,?,?,?,?,'1','1',now())";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
							requestId,
							vmlogin.getDealer_name(),
							vmlogin.getLocation(),
							vmlogin.getAddress(),
							vmlogin.getCtp_name(),
							vmlogin.getCtp_mobileno(),
							vmlogin.getAppt_dt(),
							vmlogin.getNo_stock(),
							vmlogin.getAuth_letter()
							
							
						
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

	@Override
	public Vmlogin details() {
		// TODO Auto-generated method stub
		Vmlogin user2 = new Vmlogin();
		try {
			
			
			List<Vmlogin> audit_vw = new ArrayList<Vmlogin>(); 
			
			String query = "SELECT MAX(sno) AS sno FROM dealer_info"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Vmlogin.class)); 
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
	
		
try {
			
			
			List<Vmlogin> audit_vw = new ArrayList<Vmlogin>(); 
			
			String query = "SELECT sno,dealer_name,no_stock FROM dealer_info WHERE sno='"+user2.getSno()+"'"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Vmlogin.class)); 
			if (audit_vw.size() > 0) {
				user2.setStatus(true);
				user2.setSno(audit_vw.get(0).getSno());
				user2.setDealer_name(audit_vw.get(0).getDealer_name());
				user2.setNo_stock(audit_vw.get(0).getNo_stock());
				
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
		
		
		return user2;

	}

	@Override
	public Vmlogin stockup(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		Vmlogin vm = new Vmlogin();
		try {
			
	
			
			int insertDealerReg_int = 0;
			
			
			
			String insertDealerReg_query = "INSERT INTO stock_info (dealer_mapped,vin_no,reg_no,stock_status,stock_dt) VALUES (?,?,?,'0',now())";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
						vmlogin.getSno(),
						vmlogin.getVin_no(),
						vmlogin.getReg_no(),
						
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

	@Override
	public List<Vmlogin> stocklist(int sno) {
		// TODO Auto-generated method stub
		Vmlogin custVehiDetailsOutObj = new Vmlogin();
	         List<Vmlogin> custDetailsList = new ArrayList<Vmlogin>();
			String pendingQuery = "SELECT vin_no,reg_no,stock_status,stock_dt FROM stock_info where dealer_mapped ='"+sno+"' ORDER BY stock_dt DESC";
		
			try {
				
				custDetailsList = this.jdbcTemplate.query(pendingQuery,
						new BeanPropertyRowMapper(Vmlogin.class));
				
			} catch (Exception e) {
				custVehiDetailsOutObj.setStatus(false);
				
			}
			return custDetailsList;
	}

	@Override
	public List<Vmlogin> dealerlist() {
		Vmlogin custVehiDetailsOutObj = new Vmlogin();
        List<Vmlogin> custDetailsList = new ArrayList<Vmlogin>();
		String pendingQuery = "SELECT sno,dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,audit_id,audit_status FROM dealer_info WHERE audit_status='1' ORDER BY insert_dt DESC";
		
		try {
			
			custDetailsList = this.jdbcTemplate.query(pendingQuery,
					new BeanPropertyRowMapper(Vmlogin.class));
			
		} catch (Exception e) {
			custVehiDetailsOutObj.setStatus(false);
			
		}
		return custDetailsList;
	}

	@Override
	public Vmlogin assign(Vmlogin vmlogin) {
		// TODO Auto-generated method stub
		Vmlogin vm = new Vmlogin();
		try {
			
	
			
			int insertDealerReg_int = 0;
			
						
			String insertDealerReg_query = "INSERT INTO assigner_info (audit_id,assign_by,assign_to,remarks,assign_dt) VALUES (?,?,?,?,now())";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
						vmlogin.getAudit_id(),
						vmlogin.getAssign_by(),
						vmlogin.getAssign_to(),
						vmlogin.getRemarks()
						
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
			
			String insertDealerReg_query ="UPDATE dealer_info SET audit_status='2' WHERE audit_id='"+vmlogin.getAudit_id()+"' ";
	
			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] {});
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
	public Vmlogin assigninfo(String audit_id) {
		// TODO Auto-generated method stub
		Vmlogin user2 = new Vmlogin();
		try {
			
			
			List<Vmlogin> audit_vw = new ArrayList<Vmlogin>(); 
			
			String query = "SELECT audit_id FROM dealer_info WHERE audit_id='"+audit_id+"'"; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Vmlogin.class)); 
			if (audit_vw.size() > 0) {
				user2.setStatus(true);
				/*user2.setSno(audit_vw.get(0).getSno());*/
				user2.setAudit_id(audit_vw.get(0).getAudit_id());
				
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
		return user2;
	}

	@Override
	public List<Vmlogin> assigndealerlist() {
		Vmlogin custVehiDetailsOutObj = new Vmlogin();
        List<Vmlogin> custDetailsList = new ArrayList<Vmlogin>();
		String pendingQuery = "SELECT sno,dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,audit_id,audit_status FROM dealer_info WHERE audit_status='2' ORDER BY insert_dt DESC";
		
		try {
			
			custDetailsList = this.jdbcTemplate.query(pendingQuery,
					new BeanPropertyRowMapper(Vmlogin.class));
			
		} catch (Exception e) {
			custVehiDetailsOutObj.setStatus(false);
			
		}
		return custDetailsList;
	}

	@Override
	public List<Vmlogin> completedealerlist() {
		// TODO Auto-generated method stub
		Vmlogin custVehiDetailsOutObj = new Vmlogin();
        List<Vmlogin> custDetailsList = new ArrayList<Vmlogin>();
		String pendingQuery = "SELECT sno,dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,audit_id,audit_status FROM dealer_info WHERE audit_status='3' ORDER BY insert_dt DESC";
		
		try {
			
			custDetailsList = this.jdbcTemplate.query(pendingQuery,
					new BeanPropertyRowMapper(Vmlogin.class));
			
		} catch (Exception e) {
			custVehiDetailsOutObj.setStatus(false);
			
		}
		return custDetailsList;
	}

}
