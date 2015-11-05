package com.sj.repository.service.Impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
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
import com.sj.model.type.PlaceEnum;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.PDFService;

@Service
public class PDFServiceImpl implements PDFService {
	@Autowired
	private ProductRepository productRepository;

	private final static String TEXT = "上海申捷卫生科技";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private PdfPCell getCell(String content, int colspan, Font fontChinese) {
		PdfPCell c = new PdfPCell(new Paragraph(content, fontChinese));
		c.setFixedHeight(67);
		c.setPaddingLeft(10);
		c.setPaddingRight(10);
		c.setColspan(colspan);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return c;
	}
	private PdfPCell getProductCell(String content, int colspan, Font fontChinese) {
		PdfPCell c = new PdfPCell(new Paragraph(content, fontChinese));
		c.setFixedHeight(38);
		c.setPaddingLeft(10);
		c.setPaddingRight(10);
		c.setColspan(colspan);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return c;
	}

	private PdfPTable getProductTable(Set<BuyProduct> buyProducts , Font fontChinese,float totalPrice) throws DocumentException {
		String[] header = { "名称", "型号", "规格", "数量", "单价/元", "产地" };
		PdfPTable table = new PdfPTable(header.length);

		table.setTotalWidth(new float[] { 29, 17, 13, 13, 15, 13 });
		
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
			if(buyProduct.getProduct().getPlaceOfProduction()==PlaceEnum.DOMESTIC)
				table.addCell(getCell("国产", 1, fontChinese));
			if(buyProduct.getProduct().getPlaceOfProduction()==PlaceEnum.IMPORTED)
				table.addCell(getCell("进口", 1, fontChinese));
		}
		table.addCell(getCell("共计："+totalPrice+"元", 6, fontChinese));
		return table;
	}

	@Override
	public byte[] getBuyRecordPdf(BuyRecord buyRecord,OutputStream out,String type) throws DocumentException, ParseException, IOException {
		BaseFont baseFontChinese = BaseFont.createFont("c:\\Windows\\Fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, 14);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		switch(type){
		case "one":
			Document documentOne = new Document(PageSize.A4, 0f, 0f, 10, 10);
			PdfWriter writeOne = PdfWriter.getInstance(documentOne,byteArrayOutputStream);
			typeOne(buyRecord, baseFontChinese, fontChinese, documentOne);
			byteArrayOutputStream.writeTo(out);
			byte[] bytesOne = byteArrayOutputStream.toByteArray();
			addWatermark(bytesOne, byteArrayOutputStream, writeOne);
			break;
		case "two":
			Rectangle rectangle = new Rectangle(PageSize.A4);
			Document documentTwo = new Document(rectangle.rotate(), 0f, 0f, 10, 10);
			PdfWriter writeTwo = PdfWriter.getInstance(documentTwo,byteArrayOutputStream);
			typeTwo(buyRecord, baseFontChinese, fontChinese, documentTwo);
			byteArrayOutputStream.writeTo(out);
			byte[] bytesTwo = byteArrayOutputStream.toByteArray();
			addWatermark(bytesTwo, byteArrayOutputStream, writeTwo);
			break;
		}
		
		return byteArrayOutputStream.toByteArray();
	}
	
	private void typeTwo(BuyRecord buyRecord, BaseFont baseFontChinese,
			Font fontChinese, Document document) throws DocumentException,
			ParseException, IOException, MalformedURLException {
		document.open();
		
		Font titleFont = new Font(baseFontChinese, 22);
		titleFont.setStyle(Font.BOLD);
		Paragraph title = new Paragraph("Ulabexpress采购清单",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		
		Image logo = Image.getInstance("http://127.0.0.1:8000/logo/logo.png");
		logo.setAbsolutePosition(40, 500);
		document.add(logo);
		
		PdfPTable infoTable = new PdfPTable(2);
		infoTable.setWidths(new int[]{30,70});
		infoTable.addCell(new PdfPCell(new Paragraph("订单编号：", fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		infoTable.addCell(new PdfPCell(new Paragraph(String.valueOf(buyRecord.getNoId()),fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		infoTable.addCell(new PdfPCell(new Paragraph("订单日期：", fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		infoTable.addCell(new PdfPCell(new Paragraph(sdf.format(buyRecord.getCreateTime().getTime()),fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		infoTable.addCell(new PdfPCell(new Paragraph("订单金额：", fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		infoTable.addCell(new PdfPCell(new Paragraph(String.valueOf(buyRecord.getPrice()),fontChinese))).setBorderColor(new BaseColor(255, 255, 255));
		infoTable.setWidthPercentage(30);
		infoTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
		document.add(infoTable);
		
		String[] headers = new String[]{"序号","商品名称","货号","品牌","规格","价格(元)","数量"};
		PdfPTable productsTable = new PdfPTable(7);
		productsTable.setWidthPercentage(91);
		productsTable.setWidths(new int[]{5,45,10,10,10,10,10});
		for (int i = 0,len = headers.length; i < len; i++) {
			PdfPCell cell = new PdfPCell(new Paragraph(headers[i],fontChinese));
			cell.setBorderWidthLeft(0f);
			cell.setBorderWidthRight(0f);
			cell.setUseBorderPadding(true);
			productsTable.addCell(cell);
		}
		Set<BuyProduct> products = buyRecord.getProducts();
		int no = 1;
		for (BuyProduct buyProduct : products) {
			Product p =productRepository.findOne(buyProduct.getId());
			productsTable.addCell(getBorderNoColor(String.valueOf(no), fontChinese));
			productsTable.addCell(getBorderNoColor(p.getName(), fontChinese));
			productsTable.addCell(getBorderNoColor(p.getModel(), fontChinese));
			productsTable.addCell(getBorderNoColor(p.getBrand().getName(), fontChinese));
			productsTable.addCell(getBorderNoColor(p.getSpecifications(), fontChinese));
			productsTable.addCell(getBorderNoColor(String.valueOf(p.getPrice()), fontChinese));
			productsTable.addCell(getBorderNoColor(String.valueOf(buyProduct.getNumber()), fontChinese));
			no++;
		}
		Paragraph countP = new Paragraph("小计："+buyRecord.getPrice(), fontChinese);
		countP.setAlignment(Element.ALIGN_RIGHT);
		PdfPCell countCell = new PdfPCell(countP);
		countCell.setColspan(7);
		countCell.setUseBorderPadding(true);
		countCell.setBorderWidthLeft(0f);
		countCell.setBorderWidthRight(0f);
		productsTable.addCell(countCell);
		document.add(productsTable);
		
		document.add(new Paragraph("     非常感谢您在优捷实验(Ulabexpress.com)采购，我们期待您的再次光临。",fontChinese));
		document.add(new Paragraph("     如果您想了解我们的商品验收、退换货政策/流程，请与我们的客服人员进行联系。",fontChinese));
		document.add(new Paragraph(""));
		document.add(new Paragraph("     备注：",fontChinese));
		document.add(new Paragraph("     1.供货期的前提为国外有现货，仓库现货以即时为准，对于停产和禁运商品我们将及时通知。",fontChinese));
		document.add(new Paragraph("     2.本报价单有效期为30天。",fontChinese));
		document.add(new Paragraph("     3.以上报价为人民币含税价。",fontChinese));
		document.add(new Paragraph("     4.本公司银行信息如下：",fontChinese));
		document.add(new Paragraph("       开户银行：",fontChinese));
		document.add(new Paragraph("       帐    户：",fontChinese));
		document.add(new Paragraph("       帐    号：",fontChinese));
		
		Rectangle re = new Rectangle(PageSize.A4);
		document.setMarginMirroringTopBottom(true);
		document.setMargins(0, 0, 10f, 10f);
		document.setPageSize(re);
		document.newPage();
		
		int num = 0;
		for (BuyProduct buyProduct: buyRecord.getProducts()) {
			if(num == 3){
				document.newPage();
				document.setPageSize(re);
				document.setMarginMirroringTopBottom(true);
				document.setMargins(0, 0, 10f, 10f);
				num = 0;
			}
			Product product = productRepository.findOne(buyProduct.getProduct().getId());
			document.add(getProductsTable(product,fontChinese));
			num++;
		}
		document.close();
	}
	
	private PdfPCell getBorderNoColor(String content,Font fontChinese){
		PdfPCell cell = new PdfPCell(new Paragraph(content,fontChinese));
		cell.setBorderWidth(0f);
		cell.setUseBorderPadding(true);
		return cell;
	}
	
	private void typeOne(BuyRecord buyRecord, BaseFont baseFontChinese,
			Font fontChinese, Document document) throws DocumentException,
			ParseException, IOException, MalformedURLException {
		document.open();
		Paragraph p = new Paragraph("         编号："+buyRecord.getNoId(), fontChinese);
		p.setSpacingBefore(40);
		p.setSpacingAfter(30);
		p.setAlignment(Element.ALIGN_LEFT);
		Font titleFont = new Font(baseFontChinese, 22);
		p.setFont(titleFont);
		document.add(p);
		document.add(getTable(buyRecord,fontChinese));
		document.newPage();
		
		int num = 0;
		for (BuyProduct buyProduct: buyRecord.getProducts()) {
			if(num==3){
				document.newPage();
				num=0;
			}
			Product product = productRepository.findOne(buyProduct.getProduct().getId());
			document.add(getProductsTable(product,fontChinese));
			num++;
		}
		
		document.close();
	}
	
	private PdfPTable getTable(BuyRecord buyRecord,Font fontChinese) throws ParseException, DocumentException, IOException{
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{18,32,18,32});
		table.setSplitLate(false);
		table.addCell(getCell("申请单位", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getCompany(), 1, fontChinese));
		table.addCell(getCell("行业", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getIndustryInfo().getName(), 1, fontChinese));
		table.addCell(getCell("申请部门", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getDepartment(), 1, fontChinese));
		table.addCell(getCell("申请人", 1, fontChinese));
		table.addCell(getCell(buyRecord.getUser().getRealName(), 1, fontChinese));
		table.addCell(getCell("项目名称", 1, fontChinese));
		table.addCell(getCell(buyRecord.getName(), 1, fontChinese));
		table.addCell(getCell("生成时间", 1, fontChinese));
		table.addCell(getCell(sdf.format(buyRecord.getCreateTime().getTime()), 1, fontChinese));
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
	
		String time = sdf.format(buyRecord.getArrivalTime().getTime());
		table.addCell(getCell(time, 3, fontChinese));
		table.setTotalWidth(650);
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
			Rectangle r = stamper.getUnderContent(i).getPdfDocument().getPageSize();
			if(r == PageSize.A4.rotate()){
				System.out.println("rotate");
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setColorFill(BaseColor.LIGHT_GRAY);
				under.setFontAndSize(baseFontChinese, 40);
				under.setTextMatrix(180, 50);
				int rise = 200;
				for (int k = 0; k < TEXT.length(); k++) {
					under.setTextRise(rise);
					char c = TEXT.charAt(k);
					under.showText(c + " ");
					rise += 15;
				}
			}else if(r == PageSize.A4){
				System.out.println("A4");
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
		}
		stamper.close();
		return bytes;
	}
	
	private PdfPTable getProductsTable(Product product,Font fontChinese) throws MalformedURLException, IOException, DocumentException{
			PdfPTable table = new PdfPTable(2);
			Image image = Image.getInstance(product.getCoverImg());
			image.setAlignment(Element.ALIGN_CENTER);
			PdfPCell imageCell = new PdfPCell();
			imageCell.addElement(image);
			imageCell.setUseAscender(true);
			imageCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			table.addCell(imageCell);
			
			PdfPTable infoTable = new PdfPTable(2);
			infoTable.setWidths(new int[]{18,32});
			infoTable.addCell(getProductCell("商品名称",1,fontChinese));
			infoTable.addCell(getProductCell(product.getName(), 1, fontChinese));
			infoTable.addCell(getProductCell("价格",1,fontChinese));
			infoTable.addCell(getProductCell(String.valueOf(product.getPrice()), 1, fontChinese));
			infoTable.addCell(getProductCell("型号",1,fontChinese));
			infoTable.addCell(getProductCell(product.getModel(), 1, fontChinese));
			infoTable.addCell(getProductCell("品牌",1,fontChinese));
			infoTable.addCell(getProductCell(product.getBrand().getName(), 1, fontChinese));
			infoTable.addCell(getProductCell("产地",1,fontChinese));
			if(product.getPlaceOfProduction()==PlaceEnum.DOMESTIC)
				infoTable.addCell(getProductCell("国产", 1, fontChinese));
			if(product.getPlaceOfProduction()==PlaceEnum.IMPORTED)
				infoTable.addCell(getProductCell("进口", 1, fontChinese));
			infoTable.addCell(getProductCell("标签", 1, fontChinese));
			infoTable.addCell(getProductCell(product.getLabel(),1, fontChinese));
			infoTable.addCell(getProductCell("上架日期", 1, fontChinese));
			infoTable.addCell(getProductCell(sdf.format(product.getCreatedTime().getTime()),1, fontChinese));
			table.addCell(infoTable);
		return table;
		
	}
}
