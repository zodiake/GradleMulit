package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.BUYCOUNT;
import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;
import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;
import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.model.Instrument;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.InstrumentService;
import com.sj.repository.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;

	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private InstrumentService instrumentService;

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			ProductStatusEnum status) {
		Page<Product> pages = repository.findByCreatedByAndStatus(user,
				pageable, status);
		List<Product> lists = pages.getContent();
		lists.stream().forEach(
				l -> {
					String count = template.opsForValue().get(
							VIEWCOUNT + l.getId().toString());
					if (count != null)
						l.setViewCount(Long.valueOf(count));
					else
						l.setViewCount(0l);

					String review = template.opsForValue().get(
							REVIEWCOUNT + l.getId().toString());
					if (review != null)
						l.setReviewCount(Long.valueOf(review));
					else
						l.setReviewCount(0l);

					String buy = template.opsForValue().get(
							BUYCOUNT + l.getId().toString());
					if (buy != null)
						l.setBuyCount(Long.valueOf(buy));
					else
						l.setBuyCount(0l);

					String collection = template.opsForValue().get(
							COLLECTIONCOUNT + l.getId().toString());
					if (collection != null)
						l.setCollectionCount(Long.valueOf(collection));
					else
						l.setCollectionCount(0l);
				});
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

		return repository.findByThirdCategoryAndStatus(category, pageable,
				ProductStatusEnum.UP);
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
				String str = "第" + (i + 1) + "个商品出错，出错原因：";
				for (int j = 0; j < vs.size(); j++) {
					ConstraintViolationImpl<Product> p = (ConstraintViolationImpl<Product>) vs
							.toArray()[j];
					str = str + p.getPropertyPath() + p.getMessage() + ";";
				}
				strs.add(str);
			}
			vs.clear();
		}
		if (strs.size() == 0) {
			repository.save(products);
		}
		return strs;
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
		List<Product> lists = pages.getContent();
		lists.stream().forEach(
				l -> {
					String count = template.opsForValue().get(
							VIEWCOUNT + l.getId().toString());
					if (count != null)
						l.setViewCount(Long.valueOf(count));
					else
						l.setViewCount(0l);

					String review = template.opsForValue().get(
							REVIEWCOUNT + l.getId().toString());
					if (review != null)
						l.setReviewCount(Long.valueOf(review));
					else
						l.setReviewCount(0l);

					String buy = template.opsForValue().get(
							BUYCOUNT + l.getId().toString());
					if (buy != null)
						l.setBuyCount(Long.valueOf(buy));
					else
						l.setBuyCount(0l);

					String collection = template.opsForValue().get(
							COLLECTIONCOUNT + l.getId().toString());
					if (collection != null)
						l.setCollectionCount(Long.valueOf(collection));
					else
						l.setCollectionCount(0l);
				});
		return pages;
	}

	@Override
	public Page<Product> findBySecondCategory(ProductCategory second,
			Pageable pageable) {
		return repository.findBySecondCategory(second, pageable);
	}

}
