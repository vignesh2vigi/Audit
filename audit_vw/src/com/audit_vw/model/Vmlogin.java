package com.audit_vw.model;


	import javax.xml.bind.annotation.XmlRootElement;

	import com.fasterxml.jackson.annotation.JsonInclude;
	import com.fasterxml.jackson.annotation.JsonInclude.Include;

	@XmlRootElement
	@JsonInclude(Include.NON_DEFAULT)
	public class Vmlogin {

		private int sno;
		private String dealer_name;
		private String location;
		private String address;
		private String ctp_name;
		private String ctp_mobileno;
		private String appt_dt;
		private int no_stock;
		private String auth_letter;
		private int audit_status;
		private int visibility;
		private boolean status;
		private String vin_no;
		private String reg_no;
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
		
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
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
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCtp_name() {
			return ctp_name;
		}
		public void setCtp_name(String ctp_name) {
			this.ctp_name = ctp_name;
		}
		public String getCtp_mobileno() {
			return ctp_mobileno;
		}
		public void setCtp_mobileno(String ctp_mobileno) {
			this.ctp_mobileno = ctp_mobileno;
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
		public String getAuth_letter() {
			return auth_letter;
		}
		public void setAuth_letter(String auth_letter) {
			this.auth_letter = auth_letter;
		}
		public int getAudit_status() {
			return audit_status;
		}
		public void setAudit_status(int audit_status) {
			this.audit_status = audit_status;
		}
		public int getVisibility() {
			return visibility;
		}
		public void setVisibility(int visibility) {
			this.visibility = visibility;
		}
}
