package com.hexaware.simplyfly.dto;

public class AuthRequest {

	
	private String username;
	private String password;
	private String newPassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AuthRequest() {
		super();
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	

}
