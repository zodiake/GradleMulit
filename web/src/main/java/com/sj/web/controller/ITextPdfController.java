package com.sj.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.sj.model.model.Product;
import com.sj.repository.service.PDFService;

@Controller
public class ITextPdfController {
	@Autowired
	private PDFService pDFService;

	// @RequestMapping(value = "/getAPdf", method = RequestMethod.GET)
	// public HttpEntity<byte[]> exportPDF(HttpServletResponse response) {
	// ByteArrayOutputStream byteArrayOutputStream = new
	// ByteArrayOutputStream();
	// Document document = new Document(PageSize.A4, 0.75F, 0.75F, 50, 40);
	// try {
	// PdfWriter.getInstance(document, byteArrayOutputStream);
	// document.open();
	// document.add(pDFService.getPdfPTable(getProducts(), "", "", "", "",
	// "", "", ""));
	// document.close();
	// response.setContentType("application/pdf");
	// OutputStream out = response.getOutputStream();
	// byteArrayOutputStream.writeTo(out);
	// byte[] byte1 = byteArrayOutputStream.toByteArray();
	// HttpHeaders header = new HttpHeaders();
	// header.setContentType(new MediaType("application", "pdf"));
	// header.set("Content-Disposition", "attachment; filename=a.pdf");
	// header.setContentLength(byte1.length);
	//
	// return new HttpEntity<byte[]>(byte1, header);
	// } catch (DocumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	@RequestMapping(value = "/getAPdf", method = RequestMethod.GET)
	public Callable<HttpEntity<byte[]>> exportPDF(HttpServletResponse response) {
		return new Callable<HttpEntity<byte[]>>() {
			@Override
			public HttpEntity<byte[]> call() throws Exception {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4, 0, 0, 50, 40);
				try {
					PdfWriter.getInstance(document, byteArrayOutputStream);
					document.open();
					document.add(pDFService.getPdfPTable(getProducts(), "110",
							"111", "112", "113", "114", "115", "116"));
					document.close();
					response.setContentType("application/pdf");
					OutputStream out = response.getOutputStream();
					byteArrayOutputStream.writeTo(out);
					byte[] byte1 = byteArrayOutputStream.toByteArray();
					HttpHeaders header = new HttpHeaders();
					header.setContentType(new MediaType("application", "pdf"));
					header.set("Content-Disposition",
							"attachment; filename=a.pdf");
					header.setContentLength(byte1.length);

					return new HttpEntity<byte[]>(byte1, header);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 11; i++) {
			Product product = new Product();
			product.setName("f e f a e f a e a d s f e a s d e f  e g e");
			product.setPrice(74000.0f);
			products.add(product);
		}
		return products;
	}

}
