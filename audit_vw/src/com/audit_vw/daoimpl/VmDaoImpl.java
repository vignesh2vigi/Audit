package com.audit_vw.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.audit_vw.dao.VmDao;
import com.audit_vw.model.User;
import com.audit_vw.model.Vmlogin;
import com.audit_vw.utility.Password;

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
			
			
			
			String insertDealerReg_query = "INSERT INTO dealer_info (dealer_name,location,address,ctp_name,ctp_mobileno,appt_dt,no_stock,auth_letter,audit_status,visibility,insert_dt) VALUES (?,?,?,?,?,?,?,?,?,?,now())";

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
							vmlogin.getAuth_letter(),
							1,1
							
						
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

}
