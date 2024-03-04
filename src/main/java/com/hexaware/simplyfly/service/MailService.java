package com.hexaware.simplyfly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.repository.BookingRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MailService implements IMailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private BookingRepository bookingRepo;

	private pdfGenerator pdfGenerator = new pdfGenerator();

	public MailService(JavaMailSender emailSender, pdfGenerator pdfGenerator) {
		this.mailSender = emailSender;
		this.pdfGenerator = pdfGenerator;
	}

	public void sendEmail(HttpServletResponse response, Integer bookingId) throws Exception {

		Bookings booking = bookingRepo.findById(bookingId).orElse(null);
		if (booking == null) {
			throw new BookingNotFoundException(bookingId.toString());
		}
		String toEmail = booking.getCustomer().getEmail();
		try {
			byte[] pdfBytes = pdfGenerator.generatePdfBytes(bookingId);

			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom("booking.simplyfly@gmail.com");
			messageHelper.setTo(toEmail);
			messageHelper.setSubject("Booking Details - " + bookingId);
			messageHelper.setText("Please find attached the booking details for Booking Id " + bookingId
					+ ". Hope you have a safe journey");
			messageHelper.addAttachment("booking_details.pdf", new ByteArrayResource(pdfBytes));

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
