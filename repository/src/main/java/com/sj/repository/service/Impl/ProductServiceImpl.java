package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.BUYCOUNT;
import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;
import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;
import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFRow;
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
import com.sj.model.model.CommonUser;
import com.sj.model.model.Consumable;
import com.sj.model.model.Content;
import com.sj.model.model.Instrument;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.Reagents;
import com.sj.model.model.SiteUser;
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
import com.sj.repository.repository.ProductSearchRepository;
import com.sj.repository.repository.ReagentsRepository;
import com.sj.repository.repository.ServiceRepository;
import com.sj.repository.search.model.ModelSearchOption;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.service.PreferProductService;
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
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private ProductSearchRepository searchRepository;

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			ProductStatusEnum status) {
		Page<Product> pages = repository.findByCreatedByAndStatus(user,
				pageable, status);
		return pages;
	}

	@Override
	public Product findUpOne(Long id) {
		Product p = repository.findByIdAndStatus(id, ProductStatusEnum.UP);
		if (p != null) {
			String count = template.opsForValue().get(
					COLLECTIONCOUNT + p.getId().toString());
			if (count == null)
				p.setCollectionCount(0l);
			else
				p.setCollectionCount(Long.valueOf(count));
		}
		return p;
	}

	@Override
	public Product findUpOneUserIsLogin(Long id, SiteUser user) {
		Product p = repository.findByIdAndStatus(id, ProductStatusEnum.UP);
		if (p != null) {
			String count = template.opsForValue().get(
					COLLECTIONCOUNT + p.getId().toString());
			if (count == null)
				p.setCollectionCount(0l);
			else
				p.setCollectionCount(Long.valueOf(count));
			List<PreferProduct> prefers = preferProductService
					.findByUser(new CommonUser(user.getId()));
			for (PreferProduct preferProduct : prefers) {
				if (p.getId() == preferProduct.getProduct().getId()) {
					p.setIsCollection(true);
					break;
				}
			}
		}
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
		return repository.findByBrandAndStatus(brand, ProductStatusEnum.UP,
				pageable);
	}

	@Override
	public Page<Product> findByCategory(ProductCategory category,
			Pageable pageable) {
		Page<Product> products = repository.findByThirdCategoryAndStatus(
				category, pageable, ProductStatusEnum.UP);
		for (Product product : products.getContent()) {
			String reviewCount = template.opsForValue().get(
					REVIEWCOUNT + product.getId());
			if (reviewCount == null)
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
		if(ProductStatusEnum.UP.equals(product.getStatus())){
			searchRepository.delete(product.getId());
		}
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
			String viewCount = template.opsForValue().get(
					VIEWCOUNT + product.getId().toString());
			if (viewCount == null)
				product.setViewCount(0l);
			else
				product.setViewCount(Long.valueOf(viewCount));

			String collectionCount = template.opsForValue().get(
					COLLECTIONCOUNT + product.getId().toString());
			if (collectionCount == null)
				product.setCollectionCount(0l);
			else
				product.setCollectionCount(Long.valueOf(collectionCount));

			String reviewCount = template.opsForValue().get(
					REVIEWCOUNT + product.getId().toString());
			if (reviewCount == null)
				product.setReviewCount(0l);
			else
				product.setReviewCount(Long.valueOf(reviewCount));

			String buyCount = template.opsForValue().get(
					BUYCOUNT + product.getId().toString());
			if (buyCount == null)
				product.setBuyCount(0l);
			else
				product.setBuyCount(Long.valueOf(buyCount));
		}
		return pages;
	}

	@Override
	public Page<Product> findBySecondCategory(ProductCategory second,
			Pageable pageable) {
		Page<Product> products = repository.findBySecondCategory(second,
				pageable);
		for (Product product : products.getContent()) {
			String reviewCount = template.opsForValue().get(
					REVIEWCOUNT + product.getId());
			if (reviewCount == null)
				product.setReviewCount(0l);
			else
				product.setReviewCount(Long.valueOf(reviewCount));

		}
		return repository.findBySecondCategory(second, pageable);
	}

	@Override
	public Page<ProductJson> findByFirstCategoryAndSecondCategoryAndThirdCategoryAndStatusJson(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductCategory tc, ProductStatusEnum status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> c = cb.createQuery(Product.class);
		Root<Product> product = c.from(Product.class);
		c.select(product);

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Product.class)));
		List<Predicate> criteria = new ArrayList<>();
		
		if (fc != null && fc.getId() != null) {
			criteria.add(cb.equal(product.get("firstCategory"), fc));
		}
		if (sc != null && sc.getId() != null) {
			criteria.add(cb.equal(product.get("secondCategory"), sc));
		}
		if (tc != null && tc.getId() != null) {
			criteria.add(cb.equal(product.get("thirdCategory"), tc));
		}
		if (status != null) {
			criteria.add(cb.equal(product.get("status"), status));
		}
		c.where(criteria.toArray(new Predicate[0]));
		c.orderBy(cb.desc(product.get("createdTime").as(Calendar.class)));
		cq.where(criteria.toArray(new Predicate[0]));

		List<Product> lists = em
				.createQuery(c)
				.setFirstResult(
						(pageable.getPageNumber() - 1) * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
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
			repository.save(p);
		} else {
			if(ProductStatusEnum.UP.equals(p.getStatus()))
				searchRepository.delete(p.getId());
			p.setStatus(ProductStatusEnum.NOT);
			repository.save(p);
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
	public List<Product> findDataForBatch(XSSFWorkbook wb, Provider provider)
			throws BatchException {
		List<Product> products = new ArrayList<Product>();
		XSSFSheet sheet = wb.getSheetAt(0);
		int irLength = sheet.getLastRowNum();
		for (int i = 3; i <= irLength; i++) {
			XSSFRow xssfRow = sheet.getRow(i);
			Product product = new Product();
			String name = getStringCellValue(xssfRow, i, 0, 255);
			product.setName(name);
			String model = getStringCellValue(xssfRow, i, 1, 50);
			product.setModel(model);

			product.setSpecifications(getStringCellValue(xssfRow, i, 2, 100));
			String brandName = getStringCellValue(xssfRow, i, 3);
			Brand brand = brandRepository.findByNameAndActivate(brandName,
					ActivateEnum.ACTIVATE);
			if (brand == null)
				throw new BatchException("第" + (i + 1) + "行品牌找不到");
			else
				product.setBrand(brand);

			String place = xssfRow.getCell(4, HSSFRow.CREATE_NULL_AS_BLANK)
					.getStringCellValue();
			if ("".equals(place))
				product.setPlaceOfProduction(PlaceEnum.OTHER);
			else if ("国产".equals(place))
				product.setPlaceOfProduction(PlaceEnum.DOMESTIC);
			else if ("进口".equals(place))
				product.setPlaceOfProduction(PlaceEnum.IMPORTED);
			else
				throw new BatchException("第" + (i + 1) + "行产地输入错误");

			float price = getNumericCellValue(xssfRow, i, 5);
			product.setPrice(price);

			String firstCategoryName = getStringCellValue(xssfRow, i, 6);
			ProductCategory firstCategory = pcRepository
					.findByNameAndActivateAndParentIsNull(firstCategoryName,
							ActivateEnum.ACTIVATE);
			if (firstCategory == null)
				throw new BatchException("第" + (i + 1) + "行大类不存在");
			else
				product.setFirstCategory(firstCategory);

			String secondCategoryName = getStringCellValue(xssfRow, i, 7);
			secondCategoryName = secondCategoryName.replaceAll("\\\\", "/");
			
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

			String label = xssfRow.getCell(9, HSSFRow.CREATE_NULL_AS_BLANK)
					.getStringCellValue();
			if (label != null && label.length() > 150)
				throw new BatchException("第" + (i + 1) + "行标签过长");
			product.setLabel(label);

			product.setCreatedBy(provider);
			product.setCreatedTime(Calendar.getInstance());
			product.setAuthenticatedTime(Calendar.getInstance());
			product.setStatus(ProductStatusEnum.UP);
			product.setCoverImg("http://139.196.30.55:8000/2015/9/noImage.jpg");
			product.setContent(new Content());

			products.add(product);
		}
		return products;
	}

	private String getStringCellValue(XSSFRow xssfRow, int line, int column,
			int length) throws BatchException {
		String value = "";
		try {
			XSSFCell cell = xssfRow.getCell(column);
			switch(cell.getCellType()){
			case XSSFCell.CELL_TYPE_NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = xssfRow.getCell(column).getStringCellValue();
				break;
			default :
				throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
						+ "列数据错误");
			}
		} catch (Exception e) {
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
					+ "列数据错误");
		}
		if (value == null || value.length() == 0)
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
					+ "列数据为空");
		if (value.length() > length)
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
					+ "列数据过长");
		return value;
	}

	private String getStringCellValue(XSSFRow xssfRow, int line, int column)
			throws BatchException {
		String value = "";
		try {
			value = xssfRow.getCell(column).getStringCellValue();
		} catch (Exception e) {
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
					+ "列数据错误");
		}
		if (value == null || value.length() == 0)
			throw new BatchException("第" + (line + 1) + "行,第" + (column + 1)
					+ "列数据为空");
		return value;
	}

	private float getNumericCellValue(XSSFRow xssfRow, int line, int column)
			throws BatchException {
		float price = 0f;
		try {
			price = (float) xssfRow.getCell(column).getNumericCellValue();
		} catch (Exception e) {
			throw new BatchException("第" + (line + 1) + "行价格输入有误");
		}
		if (price == 0f)
			throw new BatchException("第" + (line + 1) + "行价格不能为0或者为空");
		return price;
	}

	@Override
	public String batchSaveProduct(InputStream is, Provider provider)
			throws BatchException, Exception {
		XSSFWorkbook wb = new XSSFWorkbook(is);
		is.close();
		List<Product> products = findDataForBatch(wb, provider);
		wb.close();
		for (Product product : products) {
			String categoryId = product.getFirstCategory().getId().toString();
			switch (categoryId) {
			case "1":
				Instrument i = new Instrument(product);
				i = instrumentRepository.save(i);
				searchService.save(new ProductSearch(i));
				continue;
			case "2":
				Reagents r = new Reagents(product);
				r = reagentsRepository.save(r);
				searchService.save(new ProductSearch(r));
				continue;
			case "3":
				Consumable c = new Consumable(product);
				c = consumableRepository.save(c);
				searchService.save(new ProductSearch(c));
				continue;
			case "4":
				com.sj.model.model.Service s = new com.sj.model.model.Service(
						product);
				s = serviceRepository.save(s);
				searchService.save(new ProductSearch(s));
				continue;
			}
		}
		return "success";
	}

	@Override
	public XSSFWorkbook getModel() throws InvalidFormatException, IOException {
		File file = Paths.get("").resolve("d:/model.xlsx").toFile();
		XSSFWorkbook wb = new XSSFWorkbook(file);
		int num = wb.getNumberOfSheets();
		if (num != 1) {
			for (int i = 1; i < num; i++) {
				wb.removeSheetAt(i);
			}
		}
		XSSFSheet brandAndPlaceSheet = wb.createSheet("品牌和产地");

		List<Brand> brands = brandRepository
				.findByActivate(ActivateEnum.ACTIVATE);
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
		List<ProductCategory> firstCategories = pcRepository
				.findByParentIsNullAndActivate(ActivateEnum.ACTIVATE);
		XSSFRow firstCategoryRow = categorySheet.createRow(0);
		for (int i = 0; i < firstCategories.size(); i++) {
			XSSFCell firstCategoryCell = firstCategoryRow.createCell(i);
			firstCategoryCell.setCellValue(firstCategories.get(i).getName());
		}

		int secondRowNum = 6;
		for (int i = 0; i < firstCategories.size(); i++) {
			XSSFRow secondRow = categorySheet.createRow(i + 2);
			XSSFCell secondCategoryHeadCell = secondRow.createCell(0);
			secondCategoryHeadCell.setCellValue(firstCategories.get(i)
					.getName());
			List<ProductCategory> secondCategories = pcRepository
					.findByParentAndActivate(firstCategories.get(i),
							ActivateEnum.ACTIVATE);

			for (int j = 0; j < secondCategories.size(); j++) {
				XSSFCell secondCell = secondRow.createCell(j + 1);
				String secondCategoryName = secondCategories.get(j).getName();
				secondCategoryName = secondCategoryName.replaceAll("/", "\\\\");
				secondCell.setCellValue(secondCategoryName);

				secondRowNum = secondRowNum + 1;
				XSSFRow thirdRow = categorySheet.createRow(secondRowNum);
				XSSFCell thirdCategoryHeadCell = thirdRow.createCell(0);
				thirdCategoryHeadCell.setCellValue(secondCategoryName);
				List<ProductCategory> thirdCategories = pcRepository
						.findByParentAndActivate(secondCategories.get(j),
								ActivateEnum.ACTIVATE);
				for (int k = 0; k < thirdCategories.size(); k++) {
					XSSFCell thirdCell = thirdRow.createCell(k + 1);
					thirdCell.setCellValue(thirdCategories.get(k).getName());
				}
			}
		}
		return wb;
	}

	@Override
	public Product findOne(Long id) {
		Product p = repository.findOne(id);
		if (p != null) {
			String count = template.opsForValue().get(
					COLLECTIONCOUNT + p.getId().toString());
			if (count == null)
				p.setCollectionCount(0l);
			else
				p.setCollectionCount(Long.valueOf(count));
		}
		return p;
	}

	@Override
	public Page<Product> findBySearchModel(ModelSearchOption option,
			Pageable pageable) {

		return repository.findByStatusAndModelLike(ProductStatusEnum.UP, "%"
				+ option.getTitle() + "%", pageable);
	}

	@Override
	public Map<String, String> buildMap(ModelSearchOption option) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);
		Map<String, String> map = new HashMap<>();
		for (Field f : options) {
			Object value;
			try {
				value = f.get(option);
				if(!f.getName().equals("title"))
				map.put(f.getName(), String.valueOf(value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public List<Field> filterNullValue(ModelSearchOption option, Field[] fields) {
		return Arrays.stream(fields).filter(f -> {
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			try {
				return f.get(option) != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}
}
