package com.airline.project.OtpDetails;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="otp_dtls", schema="air_otp")


public class OtpDtls {
	
	@Id
	@Column(name="ref_id")
	private String refId;
	
	@Column(name="gnrtd_otp")
	private String generatedOtp;
	
	@Column(name="otp_attmpt")
	private Integer otpAttempt;
	
	@Column(name="otp_gnrt_tm")
	private LocalDateTime otpGenerateTime;
	
	@Column(name = "usr_vldty")
	private Boolean usrVldty = false; 

	public Boolean getUsrVldty() {
		return usrVldty;
	}

	public void setUsrVldty(Boolean usrVldty) {
		this.usrVldty = usrVldty;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getGeneratedOtp() {
		return generatedOtp;
	}

	public void setGeneratedOtp(String generatedOtp) {
		this.generatedOtp = generatedOtp;
	}

	public Integer getOtpAttempt() {
		return otpAttempt;
	}

	public void setOtpAttempt(Integer otpAttempt) {
		this.otpAttempt = otpAttempt;
	}

	public LocalDateTime getOtpGenerateTime() {
		return otpGenerateTime;
	}

	public void setOtpGenerateTime(LocalDateTime localDateTime) {
		this.otpGenerateTime = localDateTime;
	}
	
	
	
	
	
}
