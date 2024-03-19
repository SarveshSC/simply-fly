package com.hexaware.simplyfly.service;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Passengers;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class pdfGenerator implements IPdfGenerator{
	
	@Autowired
	 BookingRepository bookingRepo;
	
	@Override
	public PdfPTable export(HttpServletResponse response,int bookingid) throws Exception{
		Bookings bookings=bookingRepo.getBookingsByBookingId(bookingid);
		
		
		PdfPTable table=null;
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		Font fontTitle=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		
		Font fontSubTitle=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(14);
		
		Paragraph paragraph=new Paragraph("Booking Details",fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		
		Font fontParagraph=FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);
		
		Paragraph paragraph1=new Paragraph("Here are the Booking detais",fontSubTitle);
		paragraph.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(paragraph1);
		
		Paragraph paragraph2=new Paragraph("BooKing Id:"+bookings.getBookingId()+"\n"+
		"Booking date and time:"+bookings.getBookingDateTime()+"\n"+"\n");
		document.add(paragraph2);
		
		Paragraph paragraph3=new Paragraph("Source:"+bookings.getFlightTripForBooking().getSource().getLocation()+"\n"+
				"Destination:"+bookings.getFlightTripForBooking().getDestination().getLocation()+"\n"+"\n");
		document.add(paragraph3);
		
		Paragraph paragraph4=new Paragraph("Source Airport:"+bookings.getFlightTripForBooking().getSource().getName()+"\n"+
				"Destination Airport:"+bookings.getFlightTripForBooking().getDestination().getName()+"\n"+"\n");
		document.add(paragraph4);
		
		
		Paragraph paragraph5=new Paragraph("Departure:"+bookings.getFlightTripForBooking().getDeparture()+"\n"+
				"Arrival:"+bookings.getFlightTripForBooking().getArrival()+"\n"+"\n");
		document.add(paragraph5);
		
		
		table=new PdfPTable(5);
		table.addCell("passengerId");
		table.addCell("name");
		table.addCell("age");
		table.addCell("gender");
		table.addCell("seat");
		
		Set<Passengers> passengers=bookings.getPassengers();
		for (Passengers passenger:passengers) {
			table.addCell(passenger.getPassengerId());
			table.addCell(passenger.getName());
			table.addCell(passenger.getAge().toString());
			table.addCell(passenger.getGender().toString());
			table.addCell(passenger.getSeat().getSeatNo().getSeatNo());
		}
		document.add(table);
		document.close();
		return table;	
	}

	@Override
	public byte[] generatePdfBytes(int bookingid) throws Exception {
        Bookings bookings = bookingRepo.getBookingsByBookingId(bookingid);

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Font fontSubTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(14);

        Paragraph paragraph = new Paragraph("Booking Details", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("Here are the Booking details", fontSubTitle);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph1);

        document.add(new Paragraph("Booking Id: " + bookings.getBookingId()));
        document.add(new Paragraph("Booking date and time: " + bookings.getBookingDateTime()));
        document.add(new Paragraph("Source: " + bookings.getFlightTripForBooking().getSource().getLocation()));
        document.add(new Paragraph("Destination: " + bookings.getFlightTripForBooking().getDestination().getLocation()));
        document.add(new Paragraph("Source Airport: " + bookings.getFlightTripForBooking().getSource().getName()));
        document.add(new Paragraph("Destination Airport: " + bookings.getFlightTripForBooking().getDestination().getName()));
        document.add(new Paragraph("Departure: " + bookings.getFlightTripForBooking().getDeparture()));
        document.add(new Paragraph("Arrival: " + bookings.getFlightTripForBooking().getArrival()));

        PdfPTable table = new PdfPTable(5);
        table.addCell("passengerId");
        table.addCell("name");
        table.addCell("age");
        table.addCell("gender");
        table.addCell("seat");

        Set<Passengers> passengers = bookings.getPassengers();
        for (Passengers passenger : passengers) {
            table.addCell(passenger.getPassengerId());
            table.addCell(passenger.getName());
            table.addCell(passenger.getAge().toString());
            table.addCell(passenger.getGender().toString());
            table.addCell(passenger.getSeat().getSeatNo().getSeatNo().toString());
        }
        document.add(table);

        document.close();

        return outputStream.toByteArray();
    }
}