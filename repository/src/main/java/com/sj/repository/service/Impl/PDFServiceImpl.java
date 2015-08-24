package com.sj.repository.service.Impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sj.model.model.Product;
import com.sj.repository.service.PDFService;

@Service
public class PDFServiceImpl implements PDFService {

	/*
	 * 
	 */
	@Override
	public PdfPTable getPdfPTable(List<Product> products,
			String departmentName, String applicant, String type,
			String projectName, String reason, String head, String logistic) {
		BaseFont baseFontChinese = null;
		try {
			baseFontChinese = BaseFont.createFont(
					"c:\\Windows\\Fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		Font fontChinese = new Font(baseFontChinese, 14);

		PdfPTable table = new PdfPTable(4);
		table.setSplitLate(false);
		table.addCell(getCell("申请科室名称:", 1, fontChinese));
		table.addCell(getCell(departmentName, 1, fontChinese));
		table.addCell(getCell("申请人:", 1, fontChinese));
		table.addCell(getCell(applicant, 1, fontChinese));
		table.addCell(getCell("经费类别", 1, fontChinese));
		table.addCell(getCell(type, 3, fontChinese));
		table.addCell(getCell("项目名称及编号", 1, fontChinese));
		table.addCell(getCell(projectName, 3, fontChinese));
		table.addCell(getCell("请购物资(外包服务)明细", 4, fontChinese));

		// 添加表格
		PdfPCell c = new PdfPCell(getTable(products, fontChinese));
		c.setColspan(4);
		c.setPaddingLeft(5);
		c.setPaddingRight(5);

		table.addCell(c);

		table.addCell(getCell("申请理由", 1, fontChinese));
		table.addCell(getCell(reason, 3, fontChinese));
		table.addCell(getCell("科室负责人", 1, fontChinese));
		table.addCell(getCell(head, 3, fontChinese));
		table.addCell(getCell("后勤管理负责人", 1, fontChinese));
		table.addCell(getCell(logistic, 3, fontChinese));
		table.setTotalWidth(600);
		table.setSplitLate(false);
		table.setSplitRows(true);
		return table;
	}

	@Override
	public PdfPCell getCell(String content, int colspan, Font fontChinese) {
		PdfPCell c = new PdfPCell(new Paragraph(content, fontChinese));
		c.setFixedHeight(75);
		c.setPaddingLeft(10);
		c.setPaddingRight(10);
		c.setColspan(colspan);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return c;
	}

	@Override
	public PdfPTable getTable(List<Product> products, Font fontChinese) {
		String[] header = { "名称", "型号及规格", "单位", "数量", "估价", "国别及产地" };
		PdfPTable table = new PdfPTable(header.length);

		try {
			table.setTotalWidth(new float[] { 210, 130, 70, 70, 100, 90 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < header.length; i++) {
			PdfPCell cell = getCell(header[i], 1, fontChinese);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(75);
			table.addCell(cell);
		}
		for (Product product : products) {
			table.addCell(getCell(product.getName(), 1, fontChinese));
			table.addCell(getCell("PMG-558270", 1, fontChinese));
			table.addCell(getCell("kit", 1, fontChinese));
			table.addCell(getCell("2", 1, fontChinese));
			table.addCell(getCell("74222.0", 1, fontChinese));
			table.addCell(getCell("BD", 1, fontChinese));
		}
		return table;
	}

}
