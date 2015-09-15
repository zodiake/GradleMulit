package com.sj.repository.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.exception.BatchException;
import com.sj.repository.model.ProductDetailJson;
import com.sj.repository.model.ProductJson;

public interface ProductService {
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			ProductStatusEnum status);

	public Page<Product> findByUsers(Provider user, Pageable pageable);
	
	public Product findOne(Long id);

	public Product findUpOne(Long id);
	
	public Product findUpOneUserIsLogin(Long id,SiteUser user);

	public void addViewCount(Long id);

	public Product addOneProduct(Product product);

	public Product findOneByUser(Provider user, Long id);

	public Product updateProduct(Product newProduct, Product oldProduct);

	public Page<Product> findByBrand(Pageable pageable, Brand brand);

	public Page<Product> findByCategory(ProductCategory category,
			Pageable pageable);

	public Page<Product> search(Specification<Product> sp, Pageable pageable);

	public Product updateStatus(Product product, ProductStatusEnum status);

	public Product saveOne(Product product);

	public Page<Product> findCount(Provider user, Pageable pageable);

	public Page<Product> findBySecondCategory(ProductCategory second,
			Pageable pageable);

	public Page<ProductJson> findByFirstCategoryAndSecondCategoryAndStatusJson(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductStatusEnum status);

	public ProductDetailJson findOneJson(Long id);

	public Product findOneAdmin(Long id);

	public Product updateState(Long id, ProductStatusEnum state);

	public void updateSolution(Long id, String lists);
	
	public String batchSaveProduct(InputStream is,Provider provider) throws BatchException, Exception;
	
	public List<Product> findDataForBatch(XSSFWorkbook wb,Provider provider) throws BatchException;
	
	public XSSFWorkbook getModel() throws InvalidFormatException, IOException;
	
}
