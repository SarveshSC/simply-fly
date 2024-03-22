package com.hexaware.simplyfly.dto;

import java.awt.image.BufferedImage;

public class UpdateProfileDTO {
String name;
String email;
 String contact;
 
	

public UpdateProfileDTO(String name, String email, String contact) {
	super();
	this.name = name;
	this.email = email;
	this.contact = contact;
}


public UpdateProfileDTO() {
	super();
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}








	
	
}
