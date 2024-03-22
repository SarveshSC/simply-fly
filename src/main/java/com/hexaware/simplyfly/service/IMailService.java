package com.hexaware.simplyfly.service;

import jakarta.servlet.http.HttpServletResponse;

public interface IMailService {
	public void sendEmail(HttpServletResponse response, Integer bookingId) throws Exception;
}