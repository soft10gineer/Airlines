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
	
	public UserOnboarding(String userId, String userFirstName, String userLastName, String userPasswordHash,
			Boolean userIsVerfied, Integer userAge, LocalDateTime createdDateTimestamp, String userGender,
			String userEmail, String userMobileNumber) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPasswordHash = userPasswordHash;
		this.userIsVerfied = userIsVerfied;
		this.userAge = userAge;
		this.createdDateTimestamp = createdDateTimestamp;
		this.userGender = userGender;
		this.userEmail = userEmail;
		this.userMobileNumber = userMobileNumber;
	}

	@Id
	@Column(name = "ref_id")
	@JsonProperty("Ref No")
	private String userId;
	
	@Column(name = "first_name")
	@JsonProperty("First Name")
	private String userFirstName;
	
	@Column(name = "last_name")
	@JsonProperty("Last Name")
	private String userLastName;
	
	@Column(name = "password_hash")
	@JsonProperty("Password")
	private String userPasswordHash;
	
	@Column(name = "is_verified")
	private Boolean userIsVerfied;
	
	@Column(name = "age")
	@JsonProperty("Age")
	private Integer userAge;
	
	@Column(name = "create_date")
	private LocalDateTime createdDateTimestamp;
	
	@Column(name = "gender")
	@JsonProperty("Gender")
	private String userGender;

	@Column(name= "email")
	@JsonProperty("Email")
	private String userEmail;
	
	@Column(name= "mbl_nb")
	@JsonProperty("Mobile Number")
	private String userMobileNumber;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPasswordHash() {
		return userPasswordHash;
	}

	public void setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
	}

	public Boolean getUserIsVerfied() {
		return userIsVerfied;
	}

	public void setUserIsVerfied(Boolean userIsVerfied) {
		this.userIsVerfied = userIsVerfied;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	
		
}
