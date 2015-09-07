package com.sj.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.model.ProductOption;
import com.sj.admin.security.SiteUserContext;
import com.sj.model.model.Product;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.exception.BatchException;
import com.sj.repository.model.ProductDetailJson;
import com.sj.repository.model.ProductJson;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SiteUserContext context;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductSearchService searchService;

	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	@ResponseBody
	private Page<ProductJson> list(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size,
			ProductOption option) {
		return productService
				.findByFirstCategoryAndSecondCategoryAndStatusJson(
						new PageRequest(page, size), option.getFirstCategory(),
						option.getSecondCategory(), option.getState());
	}

	@RequestMapping(value = "/admin/products/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ProductDetailJson findOne(@PathVariable("id") Long id) {
		return new ProductDetailJson(productService.findOneAdmin(id));
	}

	@RequestMapping(value = "/admin/products/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	private String updateProductState(@PathVariable("id") Long id,
			ProductStatusEnum state) {
		Product p = productService.updateState(id, state);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/products/{id}/solutions", method = RequestMethod.POST)
	@ResponseBody
	private String addSolution(@PathVariable("id") Long id,
			HttpServletRequest request) {
		String solutions = request.getParameter("solutions");
		productService.updateSolution(id, solutions);
		return "";
	}

	@RequestMapping(value = "/admin/products", method = RequestMethod.POST, params = "batch")
	@ResponseBody
	public String createBatchProcess(@RequestParam("file") MultipartFile mf) throws IOException, InvalidFormatException {
		InputStream is = mf.getInputStream();
		String result = "";
		try {
			result = productService.batchSaveProduct(is);
		} catch (IOException e) {
			return e.getMessage();
		} catch (BatchException e) {
			return e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/getModel", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadModel(HttpServletResponse response)
			throws InvalidFormatException, IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		getModel().write(out);
		byte[] b = out.toByteArray();

		response.reset();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "vnd.ms-excel"));
		header.setContentDispositionFormData("attachment", "model.xlsx");
		header.setContentLength(b.length);
		return new ResponseEntity<byte[]>(b, header, HttpStatus.CREATED);
	}

	private XSSFWorkbook getModel() throws InvalidFormatException, IOException {
		File file = Paths.get("")
				.resolve("src/main/resources/static/excel/model.xlsx").toFile();
		XSSFWorkbook wb = new XSSFWorkbook(file);
		int num = wb.getNumberOfSheets();
		if (num != 1) {
			for (int i = 1; i < num; i++) {
				wb.removeSheetAt(i);
			}
		}
		XSSFSheet sheet = wb.createSheet("品牌");
		for (int i = 0; i < 20; i++) {
			XSSFRow row = sheet.createRow(i);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue("哈哈3");
		}
		return wb;
	}
}
