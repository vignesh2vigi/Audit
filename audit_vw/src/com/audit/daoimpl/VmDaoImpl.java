package com.audit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.audit.dao.VmDao;
import com.audit.model.Vmlogin;

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
			
	
			
			int insertDealerReg_int = 0;
			
			
			
			String insertDealerReg_query = "INSERT INTO dealer_info (dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,no_stock,auth_letter,audit_status,visibility,insert_dt) VALUES (?,?,?,?,?,?,?,?,1,1,now())";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
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
	
		System.out.println(""+user2.getSno());
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
			System.out.println("pendingQuery------------------------------------->"+pendingQuery);
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
		String pendingQuery = "SELECT sno,dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt FROM dealer_info ORDER BY appt_dt DESC";
		System.out.println("pendingQuery------------------------------------->"+pendingQuery);
		try {
			
			custDetailsList = this.jdbcTemplate.query(pendingQuery,
					new BeanPropertyRowMapper(Vmlogin.class));
			
		} catch (Exception e) {
			custVehiDetailsOutObj.setStatus(false);
			
		}
		return custDetailsList;
	}

}
