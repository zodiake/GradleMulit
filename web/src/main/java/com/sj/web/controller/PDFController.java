package com.sj.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class PDFController {

	@RequestMapping(value = { "/provider/exportPDF", "/manufacture/exportPDF" })
	public void exportPDF(HttpServletResponse response) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F,
				0.75F);
		try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			document.add(new Paragraph("Hello World!"));
			document.close();
			response.setContentType("application/pdf");
			OutputStream out=response.getOutputStream();
			byteArrayOutputStream.writeTo(out);
			out.flush();
			out.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
