package com.hexaware.simplyfly.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//import com.itextpdf.text.pdf.PdfPTable;

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
		if(booking == null) {
			throw new BookingNotFoundException(bookingId.toString());
		}
		String toEmail = booking.getCustomer().getEmail();
		try {
		
		PdfPTable pdfTable = pdfGenerator.export(response, bookingId);
		byte[] pdfBytes = convertPdfTableToBytes(pdfTable);

        MimeMessage message = mailSender.createMimeMessage();
        	
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom("booking.simplyfly@gmail.com");
        messageHelper.setTo(toEmail);
        messageHelper.setSubject("Booking Details");
        messageHelper.setText("Please find attached the booking details for Booking Id " + bookingId + ". Hope you have a safe journey");
        messageHelper.addAttachment("booking_details.pdf", new ByteArrayResource(pdfBytes),"application/pdf");
        
        mailSender.send(message);
        }
		catch(MessagingException e) {
        	e.printStackTrace();
        }
	}

	private byte[] convertPdfTableToBytes(PdfPTable pdfTable) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        pdfTable.setWidthPercentage(100); // Set table width to 100% of page width
        pdfTable.setSpacingBefore(10f); // Add some space before the table
        pdfTable.setSpacingAfter(10f); // Add some space after the table
        document.add(pdfTable);
        document.close();

        writer.close(); // Close the writer

        return outputStream.toByteArray();
    }
}
