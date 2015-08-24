package com.sj.repository.service;

import java.util.List;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sj.model.model.Product;

public interface PDFService {
	public PdfPTable getPdfPTable(List<Product> products, String departmentName,
			String applicant, String type, String projectName, String reason,
			String head, String logistic);

	public PdfPCell getCell(String content, int colspan,Font fontChinese);

	public PdfPTable getTable(List<Product> products,Font fontChinese);
	
}
