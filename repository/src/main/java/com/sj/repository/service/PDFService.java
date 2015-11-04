package com.sj.repository.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;

import com.itextpdf.text.DocumentException;
import com.sj.model.model.BuyRecord;

public interface PDFService {

	public byte[] getBuyRecordPdf(BuyRecord buyRecord, OutputStream out,String type)
			throws DocumentException, ParseException, IOException;

}
