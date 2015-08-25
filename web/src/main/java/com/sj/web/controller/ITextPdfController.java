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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sj.model.model.Product;
import com.sj.repository.service.PDFService;

@Controller
public class ITextPdfController {

	private final static String TEXT = "上海申捷卫生科技";
	@Autowired
	private PDFService pDFService;

	@RequestMapping(value = "/getAPdf", method = RequestMethod.GET)
	public Callable<HttpEntity<byte[]>> exportPDF(HttpServletResponse response) {
		return new Callable<HttpEntity<byte[]>>() {
			@Override
			public HttpEntity<byte[]> call() throws Exception {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4, 0, 0, 50, 40);
				try {
					PdfWriter write = PdfWriter.getInstance(document,
							byteArrayOutputStream);
					document.open();
					document.add(pDFService.getPdfPTable(getProducts(), "110",
							"111", "112", "113", "114", "115", "116"));
					/* 新建一页 */
					document.close();

					OutputStream out = response.getOutputStream();
					byteArrayOutputStream.writeTo(out);
					byte[] byte1 = byteArrayOutputStream.toByteArray();

					/* 添加图片型水印 */

					/* 添加文字型水印 */
					PdfReader reader = new PdfReader(byte1);
					PdfStamper stamper = new PdfStamper(reader, out);
					PdfContentByte under = null;
					BaseFont baseFontChinese = null;

					try {
						baseFontChinese = BaseFont.createFont(
								"c:\\Windows\\Fonts\\SIMSUN.TTC,1",
								BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
					} catch (DocumentException | IOException e) {
						e.printStackTrace();
					}
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

					response.setContentType("application/pdf");
					out = response.getOutputStream();
					byteArrayOutputStream.writeTo(out);
					byte1 = byteArrayOutputStream.toByteArray();
					HttpHeaders header = new HttpHeaders();
					header.setContentType(new MediaType("application", "pdf"));
					header.set("Content-Disposition",
							"attachment; filename=a.pdf");
					header.setContentLength(byte1.length);

					return new HttpEntity<byte[]>(byte1, header);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 18; i++) {
			Product product = new Product();
			product.setName("张三李四王五");
			product.setPrice(74000.0f);
			products.add(product);
		}
		return products;
	}
}
