package com.airline.project.Login;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usr_login_credentials", schema="air_login")
public class UserLoginRegister {
	
	
	public UserLoginRegister() {}
	public UserLoginRegister(String email, String userKey, String userPasswrd) {
		this.userEmail = email;
		this.userKey = userKey;
		this.userPasswrd = userPasswrd;
	}

	@Id
	@Column(name="email")
	@JsonProperty("Email")
	private String userEmail;
	
	@JsonProperty("Ref Key")
	@Column(name="usr_ref_key")
	private String userKey;
	
	@JsonProperty("Password")
	@Column(name="pswrd")
	private String userPasswrd;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserPasswrd() {
		return userPasswrd;
	}

	public void setUserPasswrd(String userPasswrd) {
		this.userPasswrd = userPasswrd;
	}

	
	
}