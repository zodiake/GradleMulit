package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.BUYCOUNT;
import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;
import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;
import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.model.Consumable;
import com.sj.model.model.Content;
import com.sj.model.model.Instrument;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.Reagents;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.PlaceEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.exception.BatchException;
import com.sj.repository.model.ProductDetailJson;
import com.sj.repository.model.ProductJson;
import com.sj.repository.repository.BrandRepository;
import com.sj.repository.repository.ConsumableRepository;
import com.sj.repository.repository.InstrumentRepository;
import com.sj.repository.repository.ProductCategoryRepository;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.repository.ReagentsRepository;
import com.sj.repository.repository.ServiceRepository;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private ProductCategoryRepository pcRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private EntityManager em;
	@Autowired
	private ProductSearchService searchService;
	@Autowired
	private ConsumableRepository consumableRepository;
	@Autowired
	private InstrumentRepository instrumentRepository;
	@Autowired
	private ReagentsRepository reagentsRepository;
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			ProductStatusEnum status) {
		Page<Product> pages = repository.findByCreatedByAndStatus(user,
				pageable, status);
		return pages;
	}

	@Override
	public Product findOne(Long id) {
		Product p = repository.findOne(id);
		String count = template.opsForValue().get(VIEWCOUNT + id.toString());
		if (count == null)
			p.setCollectionCount(0l);
		else
			p.setCollectionCount(Long.valueOf(count));
		return p;
	}

	@Override
	public Product findOneAdmin(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void addViewCount(Long id) {
		template.opsForValue().increment(VIEWCOUNT + id, 1);
	}

	@Override
	public Product addOneProduct(Product product) {
		product.setCreatedTime(Calendar.getInstance());
		product.setViewCount(0L);
		return repository.save(product);
	}

	@Override
	public Product findOneByUser(Provider user, Long id) {
		return repository.findByIdAndCreatedBy(id, user);
	}

	@Override
	public Product updateProduct(Product newProduct, Product oldProduct) {
		return repository.save(oldProduct);
	}

	@Override
	public Page<Product> findByBrand(Pageable pageable, Brand brand) {
		return repository.findByBrand(brand, pageable);
	}

	@Override
	public Page<Product> findByCategory(ProductCategory category,
			Pageable pageable) {
		Page<Product> products = repository.findByThirdCategoryAndStatus(category, pageable,ProductStatusEnum.UP);
		for (Product product : products.getContent()) {
			String reviewCount = template.opsForValue().get(REVIEWCOUNT +product.getId());
			if(reviewCount==null)
				product.setReviewCount(0l);
			else
				product.setReviewCount(Long.valueOf(reviewCount));
		}
		return products;
	}

	@Override
	public Page<Product> search(Specification<Product> sp, Pageable pageable) {
		return repository.findAll(sp, pageable);
	}

	@Override
	public Product updateStatus(Product product, ProductStatusEnum status) {
		product.setStatus(status);
		return repository.save(product);
	}

	@Override
	public Product saveOne(Product product) {
		product.setCreatedTime(Calendar.getInstance());
		product.setStatus(ProductStatusEnum.EXAMINE);
		return product;
	}

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable) {
		return repository.findByCreatedBy(user, pageable);
	}

	@Override
	public Page<Product> findCount(Provider user, Pageable pageable) {
		Page<Product> pages = repository.findByCreatedBy(user, pageable);
		for (Product product : pages.getContent()) {
			String viewCount = template.opsForValue().get(VIEWCOUNT + product.getId().toString());
			if(viewCount==null)
				product.setViewCount(0l);
			else
				product.setViewCount(Long.valueOf(viewCount));
			
			String collectionCount = template.opsForValue().get(COLLECTIONCOUNT+product.getId().toString());
			if(collectionCount==null)
				product.setCollectionCount(0l);
			else
				product.setCollectionCount(Long.valueOf(collectionCount));
			
			String reviewCount = template.opsForValue().get(REVIEWCOUNT+product.getId().toString());
			if(reviewCount==null)
				product.setReviewCount(0l);
			else
				product.setReviewCount(Long.valueOf(reviewCount));
			
			String buyCount = template.opsForValue().get(BUYCOUNT+product.getId().toString());
			if(buyCount==null)
				product.setBuyCount(0l);
			else
				product.setBuyCount(Long.valueOf(buyCount));
		}
		return pages;
	}

	@Override
	public Page<Product> findBySecondCategory(ProductCategory second,
			Pageable pageable) {
		Page<Product> products = repository.findBySecondCategory(second, pageable);
		for (Product product : products.getContent()) {
			String reviewCount = template.opsForValue().get(REVIEWCOUNT +product.getId());
			if(reviewCount==null)
				product.setReviewCount(0l);
			else
				product.setReviewCount(Long.valueOf(reviewCount));
				
		}
		return repository.findBySecondCategory(second, pageable);
	}

	@Override
	public Page<ProductJson> findByFirstCategoryAndSecondCategoryAndStatusJson(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductStatusEnum status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> c = cb.createQuery(Product.class);
		Root<Product> product = c.from(Product.class);
		c.select(product);

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Product.class)));
		List<Predicate> criteria = new ArrayList<>();

		if (fc != null) {
			criteria.add(cb.equal(product.get("firstCategory"), fc));
		}
		if (sc != null) {
			criteria.add(cb.equal(product.get("secondCategory"), sc));
		}
		if (status != null) {
			criteria.add(cb.equal(product.get("status"), status));
		}
		c.where(criteria.toArray(new Predicate[0]));
		cq.where(criteria.toArray(new Predicate[0]));

		List<Product> lists = em
				.createQuery(c)
				.setFirstResult(
						(pageable.getPageNumber() - 1) * pageable.getPageSize())
				.setMaxResults(
						pageable.getPageNumber() * pageable.getPageSize())
				.getResultList();
		List<ProductJson> products = lists.stream()
				.map(m -> new ProductJson(m)).collect(Collectors.toList());

		Long count = em.createQuery(cq).getSingleResult();
		return new PageImpl<ProductJson>(products, pageable, count);
	}

	@Override
	public ProductDetailJson findOneJson(Long id) {
		return new ProductDetailJson(findOne(id));
	}

	@Override
	public Product updateState(Long id, ProductStatusEnum state) {
		Product p = repository.findOne(id);
		if (state.equals(ProductStatusEnum.UP)) {
			searchService.save(new ProductSearch(p));
			p.setStatus(ProductStatusEnum.UP);
		} else {
			p.setStatus(ProductStatusEnum.NOT);
		}
		return p;
	}

	@Override
	public void updateSolution(Long id, String lists) {
		String[] solutions = lists.split(",");
		em.createNativeQuery(
				"delete from solution_product where product_id=:productId")
				.setParameter("productId", id).executeUpdate();
		Arrays.asList(solutions)
				.forEach(
						j -> {
							em.createNativeQuery(
									"insert into solution_product (solution_id,product_id) values (:solutionId,:productId)")
									.setParameter("solutionId", j)
									.setParameter("productId", id)
									.executeUpdate();
						});

	}

	@Override
	public List<Product> findDataForBatch(XSSFWorkbook wb)
			throws BatchException {
		List<Product> products = new ArrayList<Product>();
		XSSFSheet sheet = wb.getSheetAt(0);
		int irLength = sheet.getLastRowNum();
		Provider p = new Provider(1l);
		for (int i = 3; i <= irLength; i++) {
			XSSFRow xssfRow = sheet.getRow(i);
			Product product = new Product();
			String name = getStringCellValue(xssfRow, i, 0,40);
			product.setName(name);
			String model = getStringCellValue(xssfRow, i, 1,20);
			product.setModel(model);

			product.setSpecifications(getStringCellValue(xssfRow, i, 2,20));
			String brandName = getStringCellValue(xssfRow, i, 3,50);
			Brand brand = brandRepository.findByNameAndActivate(brandName,
					ActivateEnum.ACTIVATE);
			if (brand == null)
				throw new BatchException("第" + (i + 1) + "行品牌找不到");
			else
				product.setBrand(brand);
			String place = getStringCellValue(xssfRow, i, 4,2);
			if ("国产".equals(place))
				product.setPlaceOfProduction(PlaceEnum.DOMESTIC);
			else if ("进口".equals(place))
				product.setPlaceOfProduction(PlaceEnum.IMPORTED);
			else
				throw new BatchException("第" + (i + 1) + "行产地输入错误");
			float price = getNumericCellValue(xssfRow, i, 5);
			product.setPrice(price);

			String firstCategoryName = getStringCellValue(xssfRow, i, 6);
			ProductCategory firstCategory = pcRepository.findByNameAndActivate(
					firstCategoryName, ActivateEnum.ACTIVATE);
			if (firstCategory == null)
				throw new BatchException("第" + (i + 1) + "行大类不存在");
			else
				product.setFirstCategory(firstCategory);

			String secondCategoryName = getStringCellValue(xssfRow, i, 7);
			ProductCategory secondCategory = pcRepository
					.findByNameAndParentAndActivate(secondCategoryName,
							firstCategory, ActivateEnum.ACTIVATE);
			if (secondCategory == null)
				throw new BatchException("第" + (i + 1) + "行一级分类不存在");
			else
				product.setSecondCategory(secondCategory);

			String thirdCategoryName = getStringCellValue(xssfRow, i, 8);
			ProductCategory thirdCategory = pcRepository
					.findByNameAndParentAndActivate(thirdCategoryName,
							secondCategory, ActivateEnum.ACTIVATE);
			if (thirdCategory == null)
				throw new BatchException("第" + (i + 1) + "行二级分类不存在");
			else
				product.setThirdCategory(thirdCategory);
			
			String label = xssfRow.getCell(9).getStringCellValue();
			if(label!=null && label.length()>150)
				throw new BatchException("第" + (i + 1) + "行标签过长");
			product.setLabel(label);
			product.setCreatedBy(p);
			product.setCreatedTime(Calendar.getInstance());
			product.setAuthenticatedTime(Calendar.getInstance());
			product.setStatus(ProductStatusEnum.UP);
			product.setCoverImg("/");
			product.setContent(new Content());

			products.add(product);
		}
		return products;
	}

	private String getStringCellValue(XSSFRow xssfRow, int line, int column,int length)throws BatchException {
		String value = "";
		try {
			value = xssfRow.getCell(column).getStringCellValue();
			}catch(Exception e){
				throw new BatchException("第" + (line + 1) + "行,第" + (column + 1) + "列数据错误");
			}
			if(value==null || value.length()==0)
				throw new BatchException("第" + (line + 1) + "行,第" + (column + 1) + "列数据为空");
			if(value.length()>length)
				throw new BatchException("第" + (line + 1) + "行,第" + (column + 1) + "列数据过长");
		return value;
	}
	private String getStringCellValue(XSSFRow xssfRow, int line, int column)throws BatchException {
		String value = "";
		try {
			value = xssfRow.getCell(column).getStringCellValue();
		}catch(Exception e){
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1) + "列数据错误");
		}
		if(value==null || value.length()==0)
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1) + "列数据为空");
		return value;
	}

	private float getNumericCellValue(XSSFRow xssfRow, int line, int column)
			throws BatchException {
		float price = 0f;
		try {
			price = (float) xssfRow.getCell(column).getNumericCellValue();
		}catch(Exception e){
			throw new BatchException("第" + (line + 1) + "行价格输入有误");
		}
		if(price==0f)
			throw new BatchException("第" + (line + 1) + "行价格不能为0或者为空");
		return price;
	}

	@Override
	public String batchSaveProduct(InputStream is) throws IOException,
			BatchException {
		XSSFWorkbook wb = null;
		wb = new XSSFWorkbook(is);
		List<Product> products = findDataForBatch(wb);
		wb.close();
		is.close();
		for (Product product : products) {
			String category = product.getFirstCategory().getName();
			switch (category) {
			case "仪器":
				Instrument i = new Instrument(product);
				i = instrumentRepository.save(i);
				continue;
			case "试剂":
				Reagents r = new Reagents(product);
				reagentsRepository.save(r);
				continue;
			case "耗材":
				Consumable c = new Consumable(product);
				consumableRepository.save(c);
				continue;
			case "服务":
				com.sj.model.model.Service s = new com.sj.model.model.Service(
						product);
				serviceRepository.save(s);
				continue;
			}
		}
		return "success";
	}

	@Override
	public XSSFWorkbook getModel() throws InvalidFormatException, IOException {
		File file = Paths.get("").resolve("src/main/resources/static/excel/model.xlsx").toFile();
		XSSFWorkbook wb = new XSSFWorkbook(file);
		int num = wb.getNumberOfSheets();
		if (num != 1) {
			for (int i = 1; i < num; i++) {
				wb.removeSheetAt(i);
			}
		}
		XSSFSheet brandAndPlaceSheet = wb.createSheet("品牌和产地");
		
		List<Brand> brands = brandRepository.findByActivate(ActivateEnum.ACTIVATE);
		for (int i = 0; i < brands.size(); i++) {
			XSSFRow brandRow = brandAndPlaceSheet.createRow(i);
			XSSFCell brandCell = brandRow.createCell(0);
			brandCell.setCellValue(brands.get(i).getName());
		}
		XSSFRow domesticRow = brandAndPlaceSheet.getRow(0);
		XSSFCell domesticCell = domesticRow.createCell(1);
		domesticCell.setCellValue("国产");
		XSSFRow importedRow = brandAndPlaceSheet.getRow(1);
		XSSFCell importedRowCell = importedRow.createCell(1);
		importedRowCell.setCellValue("进口");
		
		XSSFSheet categorySheet = wb.createSheet("分类");
		List<ProductCategory> firstCategories = pcRepository.findByParentIsNullAndActivate(ActivateEnum.ACTIVATE);
		XSSFRow firstCategoryRow =categorySheet.createRow(0);
		for (int i = 0; i < firstCategories.size(); i++) {
			XSSFCell firstCategoryCell = firstCategoryRow.createCell(i);
			firstCategoryCell.setCellValue(firstCategories.get(i).getName());	
		}
		
		int secondRowNum = 6;
		for (int i = 0; i < firstCategories.size(); i++) {
			XSSFRow secondRow =categorySheet.createRow(i+2);
			XSSFCell secondCategoryHeadCell = secondRow.createCell(0);
			secondCategoryHeadCell.setCellValue(firstCategories.get(i).getName());
			List<ProductCategory> secondCategories = pcRepository.findByParentAndActivate(firstCategories.get(i), ActivateEnum.ACTIVATE);
			
			for (int j = 0; j < secondCategories.size(); j++) {
				XSSFCell secondCell = secondRow.createCell(j+1);
				secondCell.setCellValue(secondCategories.get(j).getName());
				
				secondRowNum= secondRowNum+1;
				XSSFRow thirdRow = categorySheet.createRow(secondRowNum);
				XSSFCell thirdCategoryHeadCell = thirdRow.createCell(0);
				thirdCategoryHeadCell.setCellValue(secondCategories.get(j).getName());
				List<ProductCategory> thirdCategories =  pcRepository.findByParentAndActivate(secondCategories.get(j), ActivateEnum.ACTIVATE);
				for (int k = 0; k < thirdCategories.size(); k++) {
					XSSFCell thirdCell = thirdRow.createCell(k+1);
					thirdCell.setCellValue(thirdCategories.get(k).getName());
				}
			}
		}
		return wb;
	}
}
