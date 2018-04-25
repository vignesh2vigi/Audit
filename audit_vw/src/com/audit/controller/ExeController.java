package com.audit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audit.model.Error;
import com.audit.model.Exe;
import com.audit.service.ExeService;
@Controller
public class ExeController {
	@Autowired
	private ExeService exeService;
	
	
	@RequestMapping(value="/Exelogin",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody Exe exe, HttpSession session){
		if(!exeService.valid(exe.getLoginId())){	
			Error error=new Error(2,"Invalid name");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		else if(!exeService.validpass(exe.getPswd())){	
			Error error=new Error(3,"Invalid  Password");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		Exe validUser= exeService.login(exe);
		
		session.setAttribute("loginId", validUser.getLoginId());
		return new ResponseEntity<Exe>(validUser,HttpStatus.OK);	
	}
	

	@RequestMapping(value="/dealerlist/{sno}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dealerlist(@PathVariable int sno,HttpSession session){
		
		 Exe list=exeService.deallist(sno);
		 return new ResponseEntity<Exe>(list,HttpStatus.OK);
}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<?> insert(@RequestBody Exe exe) {
		System.out.println("exe register");
		
		Exe dealerRegLoginObj = exeService.insert(exe);
		return new ResponseEntity<Exe>(dealerRegLoginObj,HttpStatus.OK);
	}
	

	@RequestMapping(value="/auditlist/{audit_id}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> stocklist(@PathVariable String audit_id,HttpSession session){
		
		Exe list=exeService.auditlist(audit_id);
		 return new ResponseEntity<Exe>(list,HttpStatus.OK);
}
	@RequestMapping(value = "/stockinsert", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<?> stockinsert(@RequestBody Exe exe) {
		System.out.println("exe register");
		
		Exe dealerRegLoginObj = exeService.stockinsert(exe);
		return new ResponseEntity<Exe>(dealerRegLoginObj,HttpStatus.OK);
	}
}
