package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.OriginalEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.ProductService;
import com.sj.repository.util.UpImageUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;

	@Autowired
	private StringRedisTemplate template;

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			String original) {
		Page<Product> pages = repository.findByCreatedByAndOriginal(user,
				pageable, OriginalEnum.valueOf(original));
		List<Product> lists = pages.getContent();
		lists.stream().forEach(
				l -> {
					String count = template.opsForValue().get(
							VIEWCOUNT + l.getId().toString());
					if (count != null)
						l.setViewCount(Long.valueOf(count));
					else
						l.setViewCount(0l);
				});
		return pages;
	}

	@Override
	public Product findOne(Long id) {
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
		product.setOriginal(OriginalEnum.IN);
		product.setCoverImg(UpImageUtil.saveImage(
				product.getImage(),
				product.getCreatedBy().getId().toString(),
				Calendar.getInstance()
						+ StringUtils.trimAllWhitespace(product
								.getImage()
								.getOriginalFilename()
								.substring(
										product.getImage()
												.getOriginalFilename()
												.lastIndexOf("."))),
				UpImageUtil.PRODUCTBASE));

		return repository.save(product);
	}

	@Override
	public Product findOneByUser(Provider user, Long id) {

		// return repository.findByIdAndCreatedBy(id, user);
		return null;
	}

	@Override
	public Product updateProduct(Product newProduct, Product oldProduct) {
		// 修改oldProduct的信息
		return repository.save(oldProduct);
	}

	@Override
	public void offProduct(Product product) {
		product.setOriginal(OriginalEnum.OUT);
		repository.save(product);
	}

	@Override
	public Page<Product> findByBrand(Pageable pageable, Brand brand) {
		return repository.findByBrand(brand, pageable);
	}

	@Override
	public Page<Product> findByCategory(ProductCategory category,
			Pageable pageable) {
		return repository.findByThirdCategoryAndStatus(category, pageable, ProductStatusEnum.UP);
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
	public List<String> saveProducts(List<Product> products) {
		List<String> strs = new ArrayList<String>();
		Validator validator = javax.validation.Validation
				.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Product>> vs = null;
		for (int i = 0; i < products.size(); i++) {
			vs = validator.validate(products.get(i));
			if (!vs.isEmpty()) {
				String str = "第" + (i+1) + "个商品出错，出错原因：";
				for (int j = 0; j < vs.size(); j++) {
					ConstraintViolationImpl<Product> p = (ConstraintViolationImpl<Product>) vs
							.toArray()[j];
					str = str + p.getPropertyPath() + p.getMessage() + ";";
				}
				strs.add(str);
			}
			vs.clear();
		}
		if(strs.size()==0){
			repository.save(products);
		}
		return strs;
	}

}
