package com.audit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audit.model.Error;
import com.audit.model.User;
import com.audit.service.UserService;



@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
		if(!userService.valid(user.getLoginId())){	
			Error error=new Error(2,"Invalid name");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		else if(!userService.validpass(user.getPswd())){	
			Error error=new Error(3,"Invalid  Password");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		User validUser= userService.login(user);
		session.setAttribute("sno", validUser.getSno());
		session.setAttribute("loginId", validUser.getLoginId());
		return new ResponseEntity<User>(validUser,HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<?> dealerRegistration(@RequestBody User user) {
		
		if(userService.valid(user.getLoginId())){	
			Error error=new Error(1,"Already user_id exist");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		User dealerRegLoginObj = userService.register(user);
		return new ResponseEntity<User>(dealerRegLoginObj,HttpStatus.OK);
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(HttpSession session){
		String admin_username=(String)session.getAttribute("loginId");
		
		if(admin_username==null){
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
		}

		session.removeAttribute("loginId");
		session.invalidate();
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	@RequestMapping(value="/assignerlist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> assignerlist(HttpSession session){
String admin_username=(String)session.getAttribute("loginId");
		
		if(admin_username==null){
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
		}
		 List<User> list=userService.assign();
		 return new ResponseEntity<List<User>>(list,HttpStatus.OK);
}
}
