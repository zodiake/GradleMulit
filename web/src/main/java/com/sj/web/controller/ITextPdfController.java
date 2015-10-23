package com.sj.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.DocumentException;
import com.sj.model.model.BuyRecord;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.PDFService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.BuyRecordNotFoundException;

@Controller
public class ITextPdfController {
	@Autowired
	private BuyRecordService buyRecordService;
	@Autowired
	private PDFService pDFService;

	@RequestMapping(value = "/user/buyRecords/{noid}.pdf", method = RequestMethod.GET)
	public Callable<HttpEntity<byte[]>> exportPDF(@PathVariable("noid")String noId,HttpServletResponse response,@SecurityUser SiteUser user) {
		BuyRecord buyRecord = buyRecordService.findByNoId(noId);
		if(buyRecord==null)
			throw new BuyRecordNotFoundException();
		return new Callable<HttpEntity<byte[]>>() {
			@Override
			public HttpEntity<byte[]> call() throws Exception {
				try {
					OutputStream out = response.getOutputStream();
					byte[] bytes = pDFService.getBuyRecordPdf(buyRecord,out);
					out = response.getOutputStream();
					HttpHeaders header = new HttpHeaders();
					header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					header.setContentDispositionFormData("attachment", buyRecord.getNoId()+".pdf");
					header.setContentLength(bytes.length);

					return new HttpEntity<byte[]>(bytes, header);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
