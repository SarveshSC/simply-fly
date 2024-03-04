package com.hexaware.simplyfly.service;

import com.lowagie.text.pdf.PdfPTable;

import jakarta.servlet.http.HttpServletResponse;

public interface IPdfGenerator {

	public PdfPTable export(HttpServletResponse response,int bookingid) throws Exception;
}
