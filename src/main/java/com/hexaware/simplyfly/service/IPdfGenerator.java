package com.hexaware.simplyfly.service;

import com.itextpdf.text.pdf.PdfPTable;

import jakarta.servlet.http.HttpServletResponse;

public interface IPdfGenerator {

	public PdfPTable export(HttpServletResponse response,int bookingid) throws Exception;

	byte[] generatePdfBytes(int bookingid) throws Exception;
}