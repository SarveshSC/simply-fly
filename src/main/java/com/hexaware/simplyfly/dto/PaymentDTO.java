package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.entities.PaymentStatus;

public class PaymentDTO {
	Integer paymentId;
	PaymentStatus status;
	
	public PaymentDTO() {
		super();
	}
	public PaymentDTO(Integer paymentId, PaymentStatus status) {
		super();
		this.paymentId = paymentId;
		this.status = status;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	
	
}
