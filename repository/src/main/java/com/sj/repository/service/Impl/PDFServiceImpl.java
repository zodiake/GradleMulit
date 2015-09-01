package com.sj.repository.service.Impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sj.model.model.BuyProduct;
import com.sj.model.model.BuyRecord;
import com.sj.model.model.Product;
import com.sj.repository.service.PDFService;

@Service
public class PDFServiceImpl implements PDFService {

	private final static String TEXT = "上海申捷卫生科技";
	/*
	 * 
	 */
//	@Override
//	public PdfPTable getPdfPTable(List<Product> products,
//			String departmentName, String applicant, String type,
//			String projectName, String reason, String head, String logistic) {
//		BaseFont baseFontChinese = null;
//		try {
//			baseFontChinese = BaseFont.createFont(
//					"c:\\Windows\\Fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H,
//					BaseFont.NOT_EMBEDDED);
//		} catch (DocumentException | IOException e) {
//			e.printStackTrace();
//		}
//		Font fontChinese = new Font(baseFontChinese, 14);
//
//		PdfPTable table = new PdfPTable(4);
//		table.setSplitLate(false);
//		table.addCell(getCell("申请科室名称:", 1, fontChinese));
//		table.addCell(getCell(departmentName, 1, fontChinese));
//		table.addCell(getCell("申请人:", 1, fontChinese));
//		table.addCell(getCell(applicant, 1, fontChinese));
//		table.addCell(getCell("经费类别", 1, fontChinese));
//		table.addCell(getCell(type, 3, fontChinese));
//		table.addCell(getCell("项目名称及编号", 1, fontChinese));
//		table.addCell(getCell(projectName, 3, fontChinese));
//		table.addCell(getCell("请购物资(外包服务)明细", 4, fontChinese));
//
//		PdfPCell c = new PdfPCell(getTable(products, fontChinese));
//		c.setColspan(4);
//		c.setPaddingLeft(5);
//		c.setPaddingRight(5);
//
//		table.addCell(c);
//
//		table.addCell(getCell("申请理由", 1, fontChinese));
//		table.addCell(getCell(reason, 3, fontChinese));
//		table.addCell(getCell("科室负责人", 1, fontChinese));
//		table.addCell(getCell(head, 3, fontChinese));
//		table.addCell(getCell("后勤管理负责人", 1, fontChinese));
//		table.addCell(getCell(logistic, 3, fontChinese));
//		table.setTotalWidth(600);
//		table.setSplitLate(false);
//		table.setSplitRows(true);
//		return table;
//	}

	private PdfPCell getCell(String content, int colspan, Font fontChinese) {
		PdfPCell c = new PdfPCell(new Paragraph(content, fontChinese));
		c.setFixedHeight(75);
		c.setPaddingLeft(10);
		c.setPaddingRight(10);
		c.setColspan(colspan);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return c;
	}

	private PdfPTable getProductTable(Set<BuyProduct> buyProducts , Font fontChinese,float totalPrice) throws DocumentException {
		String[] header = { "名称", "型号", "规格", "数量", "小计/元", "产地" };
		PdfPTable table = new PdfPTable(header.length);

		table.setTotalWidth(new float[] { 210, 130, 70, 70, 100, 90 });
		
		for (int i = 0; i < header.length; i++) {
			PdfPCell cell = getCell(header[i], 1, fontChinese);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(75);
			table.addCell(cell);
		}
		for (BuyProduct buyProduct : buyProducts) {
			Product product = buyProduct.getProduct();
			table.addCell(getCell(product.getName(), 1, fontChinese));
			table.addCell(getCell(product.getModel(), 1, fontChinese));
			table.addCell(getCell(product.getSpecifications(), 1, fontChinese));
			table.addCell(getCell(buyProduct.getNumber().toString(), 1, fontChinese));
			table.addCell(getCell(String.valueOf(buyProduct.getProduct().getPrice()), 1, fontChinese));
			table.addCell(getCell(buyProduct.getProduct().getPlaceOfProduction().toString(), 1, fontChinese));
		}
		table.addCell(getCell("共计："+totalPrice+"元", 6, fontChinese));
		return table;
	}

	@Override
	public byte[] getBuyRecordPdf(BuyRecord buyRecord,OutputStream out) throws DocumentException, ParseException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 0, 0, 50, 40);
		PdfWriter write = PdfWriter.getInstance(document,byteArrayOutputStream);
		document.open();
		document.add(getTable(buyRecord));
		document.close();
		
		byteArrayOutputStream.writeTo(out);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		addWatermark(bytes, byteArrayOutputStream, write);
		return byteArrayOutputStream.toByteArray();
	}
	
	private PdfPTable getTable(BuyRecord buyRecord) throws ParseException, DocumentException, IOException{
		BaseFont baseFontChinese = BaseFont.createFont("c:\\Windows\\Fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, 14);

		PdfPTable table = new PdfPTable(4);
		table.setSplitLate(false);
		table.addCell(getCell("申请单位:", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getCompany(), 1, fontChinese));
		table.addCell(getCell("行业:", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getIndustryInfo().getName(), 1, fontChinese));
		table.addCell(getCell("申请部门:", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getDepartment(), 1, fontChinese));
		table.addCell(getCell("申请人:", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getRealName(), 1, fontChinese));
		table.addCell(getCell("编号", 1, fontChinese));
		table.addCell(getCell(buyRecord.getNoId(), 1, fontChinese));
		table.addCell(getCell("项目名称", 1, fontChinese));
		table.addCell(getCell(buyRecord.getName(), 1, fontChinese));
		table.addCell(getCell("经费类别", 1, fontChinese));
		table.addCell(getCell(buyRecord.getFundCategory(), 3, fontChinese));
		
		table.addCell(getCell("请购物资(外包服务)明细", 4, fontChinese));
		PdfPCell c = new PdfPCell(getProductTable(buyRecord.getProducts(), fontChinese,buyRecord.getPrice()));
		c.setColspan(4);
		c.setPaddingLeft(5);
		c.setPaddingRight(5);
		table.addCell(c);
		
		table.addCell(getCell("申请理由", 1, fontChinese));
		table.addCell(getCell(buyRecord.getReason(), 3, fontChinese));
		table.addCell(getCell("期望到货日期", 1, fontChinese));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(buyRecord.getArrivalTime().getTime());
		table.addCell(getCell(time, 3, fontChinese));
		table.setTotalWidth(600);
		table.setSplitLate(false);
		table.setSplitRows(true);
		return table;
	}
	
	private byte[] addWatermark(byte[] bytes,OutputStream out,PdfWriter write) throws DocumentException, IOException{
		PdfReader reader = new PdfReader(bytes);
		PdfStamper stamper = new PdfStamper(reader, out);
		PdfContentByte under = null;
		BaseFont baseFontChinese = null;
			baseFontChinese = BaseFont.createFont(
					"c:\\Windows\\Fonts\\SIMSUN.TTC,1",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		for (int i = 1; i < write.getPageNumber(); i++) {
			under = stamper.getUnderContent(i);
			under.beginText();
			under.setColorFill(BaseColor.LIGHT_GRAY);
			under.setFontAndSize(baseFontChinese, 40);
			under.setTextMatrix(70, 0);
			int rise = 250;
			for (int k = 0; k < TEXT.length(); k++) {
				under.setTextRise(rise);
				char c = TEXT.charAt(k);
				under.showText(c + " ");
				rise += 50;
			}
		}
		stamper.close();
		return bytes;
	}
}
