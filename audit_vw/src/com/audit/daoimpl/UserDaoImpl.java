package com.audit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.audit.dao.UserDao;
import com.audit.model.User;
import com.audit.utility.Password;

public class UserDaoImpl implements UserDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		/*User userOutObj = new User();*/
		User user2 = new User();
		try {
			Password td= new Password();
			
			  String pass=td.encrypt(user.getPswd());
				System.out.println(""+pass);
			
			List<User> audit_vw = new ArrayList<User>(); 
			
			String query = "SELECT loginId,first_name,login_type FROM login_info WHERE loginId='"+user.getLoginId()+"' AND pswd='"+pass+"' AND active_status='1' "; 
			
			audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(User.class)); 
			if (audit_vw.size() > 0) {
				user2.setStatus(true);
				user2.setLoginId(audit_vw.get(0).getLoginId());
				user2.setFirst_name(audit_vw.get(0).getFirst_name());
				user2.setLogin_type(audit_vw.get(0).getLogin_type());
				
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
	public boolean valid(String loginId) {
		
		User bank = new User();
		List<User> bankModelObjArray = new ArrayList<User>(); 
		String query = "SELECT loginId FROM login_info WHERE loginId='"+loginId+"'"; 
	                                        
		bankModelObjArray = getJdbcTemplate().query(query, new BeanPropertyRowMapper(User.class)); 
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
			User bank = new User();
			
			 Password td=new Password();
			 
				  String pass=td.encrypt(pswd);
					System.out.println("valid"+pass);
			
				List<User> bankModelObjArray = new ArrayList<User>(); 
				String query = "SELECT pswd FROM login_info WHERE pswd='"+pass+"'"; 
				
				bankModelObjArray = getJdbcTemplate().query(query, new BeanPropertyRowMapper(User.class)); 
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
	public User register(User user) {
		
		
		// TODO Auto-generated method stub
		User dealerRegObj = new User();
		try {
			
			Password td= new Password();
			
	      String pass=td.encrypt(user.getPswd());
			System.out.println(""+pass);
			
			int insertDealerReg_int = 0;
			
			
			
			String insertDealerReg_query = "INSERT INTO login_info (loginId,first_name,pswd,emailId) VALUES (?,?,?,?)";

			insertDealerReg_int = this.jdbcTemplate.update(
					insertDealerReg_query,
					new Object[] { 
							user.getLoginId(),
							user.getFirst_name(),
							pass,
							user.getEmailId()
							
							
						
							});
			if (insertDealerReg_int > 0) {
				dealerRegObj.setStatus(true);
				dealerRegObj.setMessage("Register Successfully...!");
			} else {
				dealerRegObj.setStatus(false);
				dealerRegObj.setMessage(" Register UnSuccessful...!");
			}
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		return dealerRegObj;
	}


	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
