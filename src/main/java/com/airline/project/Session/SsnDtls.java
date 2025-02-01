package com.airline.project.Session;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ssn_db", schema="air_ssn")
public class SsnDtls {
	
	
	public SsnDtls() {
	}
	
	public SsnDtls(String ssnRefNo, LocalDateTime date) {
		super();
		this.ssnRefNo = ssnRefNo;
		this.ssnCrtDt = date;
	}

	@Id
	@Column(name="ref_no")
	private String ssnRefNo;
	
	@Column(name="crtd_dt")
	private LocalDateTime ssnCrtDt;

	public String getSsnRefNo() {
		return ssnRefNo;
	}

	public void setSsnRefNo(String ssnRefNo) {
		this.ssnRefNo = ssnRefNo;
	}

	public LocalDateTime getSsnCrtDt() {
		return ssnCrtDt;
	}

	public void setSsnCrtDt(LocalDateTime ssnCrtDt) {
		this.ssnCrtDt = ssnCrtDt;
	}
	
	
}
