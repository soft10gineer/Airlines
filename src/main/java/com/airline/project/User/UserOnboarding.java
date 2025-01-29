package com.airline.project.User;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "user_onboarding", schema = "air_user")
public class UserOnboarding {
	
	public UserOnboarding() {
    }
	
	
	public UserOnboarding(String userId, String userName, Integer userAge, LocalDateTime createdDateTimestamp,
			String userGender, String userEmail, String userMobileNumber) {
		
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.createdDateTimestamp = createdDateTimestamp;
		this.userGender = userGender;
		this.userEmail= userEmail;
		this.userMobileNumber = userMobileNumber;
	}


	@Id
	@Column(name = "ref_id")
	@JsonProperty("Ref No")
	private String userId;
	
	@Column(name = "name")
	private String userName;
	
	@Column(name = "age")
	private Integer userAge;
	
	@Column(name = "create_date")
	private LocalDateTime createdDateTimestamp;
	
	@Column(name = "gender")
	private String userGender;

	@Column(name= "email")
	private String userEmail;
	
	@JsonProperty("Mobile Number")
	@Column(name= "mbl_nb")
	private String userMobileNumber;
	
	
	public String getUserMobileNumber() {
		return userMobileNumber;
	}


	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getUserAge() {
		return userAge;
	}


	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}


	public LocalDateTime getCreatedDateTimestamp() {
		return createdDateTimestamp;
	}


	public void setCreatedDateTimestamp(LocalDateTime createdDateTimestamp) {
		this.createdDateTimestamp = createdDateTimestamp;
	}


	public String getUserGender() {
		return userGender;
	}


	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	
}
