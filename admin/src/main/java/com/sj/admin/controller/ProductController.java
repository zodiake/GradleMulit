package com.sj.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.security.SiteUserContext;
import com.sj.admin.util.AdminSeachProductForm;
import com.sj.model.model.Brand;
import com.sj.model.model.Content;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.type.OriginalEnum;
import com.sj.model.type.PlaceEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SiteUserContext context;

	@RequestMapping(value = "/admins/product", method = RequestMethod.GET, params = "batch")
	public String createBatch() {

		return "create";
	}

	@RequestMapping(value = "/admin/product/search", method = RequestMethod.GET)
	public String seachProduct(
			@ModelAttribute("form") AdminSeachProductForm form, Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		Specification<Product> specification = new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate
						.getExpressions();
				if (form.getType() != null
						&& NumberUtils.isNumber(form.getType().getId()
								.toString())) {
					expressions.add(cb.equal(root.get("firstCategory")
							.get("id"), form.getType().getId()));
				}
				if (form.getFirst() != null
						&& NumberUtils.isNumber(form.getFirst().getId()
								.toString())) {
					expressions.add(cb.equal(
							root.get("secondCategory").get("id"), form
									.getFirst().getId()));
				}
				if (form.getSecond() != null
						&& NumberUtils.isNumber(form.getSecond().getId()
								.toString())) {
					expressions.add(cb.equal(root.get("thirdCategory")
							.get("id"), form.getSecond().getId()));
				}
				if (form.getStatus() != null) {
					expressions.add(cb.equal(root.get("status"),
							form.getStatus()));
				}

				return predicate;
			}
		};
		Page<Product> products = productService.search(specification,
				new PageRequest(page - 1, size));
		uiModel.addAttribute("products", products);
		uiModel.addAttribute("form", form);
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.GET)
	public String findOne(Model uiModel, @PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		uiModel.addAttribute("product", product);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.DELETE)
	public String deleteOne(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			return "fail";
		productService.updateStatus(product, ProductStatusEnum.DOWN);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.POST)
	public String upOne(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			return "fail";
		productService.updateStatus(product, ProductStatusEnum.UP);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.PUT)
	public String notOne(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			return "fail";
		productService.updateStatus(product, ProductStatusEnum.NOT);
		return "success";
	}

	@RequestMapping(value = "/admins/product", method = RequestMethod.POST, params = "batch")
	@ResponseBody
	public String createBatchProcess(@RequestParam("file") MultipartFile mf)
			throws IOException, InvalidFormatException {
		InputStream is = mf.getInputStream();
		List<Product> products = getData(is);
		List<String> strs = productService.saveProducts(products);
		return strs.toString();
	}

	private List<Product> getData(InputStream is)
			throws InvalidFormatException, IOException {
		XSSFWorkbook wb = new XSSFWorkbook(is);

		XSSFSheet sheet = wb.getSheetAt(0);
		// 总行数
		int irLength = sheet.getLastRowNum();
		// Product[] products = new Product[irLength - 2];
		List<Product> products = new ArrayList<Product>();
		// 得到行
		// XSSFRow row = sheet.getRow(0);
		// 总列数
		// int idLength = row.getLastCellNum();

		// Provider p = (Provider) context.getCurrnetUser();
		Provider p = new Provider(1l);

		for (int i = 3; i <= irLength; i++) {
			try {
				XSSFRow hssfRow = sheet.getRow(i);
				Product product = new Product();
				product.setName(hssfRow.getCell(1).getStringCellValue());
				XSSFCell nameEnglish = hssfRow.getCell(2);
				if (nameEnglish != null) {
					product.setNameEnglish(nameEnglish.getStringCellValue());
				}
				product.setModel(hssfRow.getCell(3).getStringCellValue());
				product.setSpecifications(hssfRow.getCell(4)
						.getStringCellValue());
				// Brand brand = new Brand();
				// brand.setName(hssfRow.getCell(5).getStringCellValue());
				product.setBrand(new Brand(1l));
				product.setPlaceOfProduction(PlaceEnum.stringToEnum(hssfRow
						.getCell(6).getStringCellValue()));
				product.setPrice((float) hssfRow.getCell(7)
						.getNumericCellValue());
				product.setFirstCategory(ProductCategory.getFirst(hssfRow
						.getCell(8).getStringCellValue()));
				// 9
				product.setSecondCategory(ProductCategory.getFirst(hssfRow
						.getCell(9).getStringCellValue()));
				// 10
				product.setThirdCategory(ProductCategory.getFirst(hssfRow
						.getCell(10).getStringCellValue()));
				product.setLabel(hssfRow.getCell(11).getStringCellValue());
				String text = hssfRow.getCell(12).getStringCellValue();
				if (text != null && text.length() != 0) {
					Content content = new Content();
					content.setContent(text);
					product.setContent(content);
				}
				product.setCreatedBy(p);
				product.setCreatedTime(Calendar.getInstance());
				product.setOriginal(OriginalEnum.PASS);

				// products[i - 3] = product;
				products.add(product);
			} catch (Exception e) {
				System.out.println(i + 1);
			}
		}
		wb.close();
		return products;
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
