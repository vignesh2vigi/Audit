package com.audit.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@JsonInclude(Include.NON_DEFAULT)
public class Exe {

	private String loginId;
	private String pswd;
	private int login_type;
	private boolean status;
	private String dealer_name;
	private String address;
	private String appt_dt;
	private int no_stock;
	private int sno;
	private String first_name;
	private String ctp_name;
	private String reached_dt;
	private String ctp_meet_dt;
	private String start_dt;
	private String audit_id;
	private String vin_no;
	private String reg_no;
	private int veh_avail_status;
	private String message;
	private List<Exe> dealerlist = new ArrayList<Exe>();
	private List<Exe> auditlist = new ArrayList<Exe>();
	public List<Exe> getAuditlist() {
		return auditlist;
	}
	public void setAuditlist(List<Exe> auditlist) {
		this.auditlist = auditlist;
	}
	public List<Exe> getDealerlist() {
		return dealerlist;
	}
	public void setDealerlist(List<Exe> dealerlist) {
		this.dealerlist = dealerlist;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getVeh_avail_status() {
		return veh_avail_status;
	}
	public void setVeh_avail_status(int veh_avail_status) {
		this.veh_avail_status = veh_avail_status;
	}
	public String getVehi_images() {
		return vehi_images;
	}
	public void setVehi_images(String vehi_images) {
		this.vehi_images = vehi_images;
	}
	public String getSubmit_proof() {
		return submit_proof;
	}
	public void setSubmit_proof(String submit_proof) {
		this.submit_proof = submit_proof;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getVerify_dt() {
		return verify_dt;
	}
	public void setVerify_dt(String verify_dt) {
		this.verify_dt = verify_dt;
	}
	private String vehi_images;
	private String submit_proof;
	private String comments;
	private String verify_dt;
	
	public String getVin_no() {
		return vin_no;
	}
	public void setVin_no(String vin_no) {
		this.vin_no = vin_no;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}
	public String getReached_dt() {
		return reached_dt;
	}
	public void setReached_dt(String reached_dt) {
		this.reached_dt = reached_dt;
	}
	public String getCtp_meet_dt() {
		return ctp_meet_dt;
	}
	public void setCtp_meet_dt(String ctp_meet_dt) {
		this.ctp_meet_dt = ctp_meet_dt;
	}
	public String getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	private String end_dt;

	public String getCtp_name() {
		return ctp_name;
	}
	public void setCtp_name(String ctp_name) {
		this.ctp_name = ctp_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAppt_dt() {
		return appt_dt;
	}
	public void setAppt_dt(String appt_dt) {
		this.appt_dt = appt_dt;
	}
	public int getNo_stock() {
		return no_stock;
	}
	public void setNo_stock(int no_stock) {
		this.no_stock = no_stock;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public int getLogin_type() {
		return login_type;
	}
	public void setLogin_type(int login_type) {
		this.login_type = login_type;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
