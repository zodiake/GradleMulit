package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.BUYCOUNT;
import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;
import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;
import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.model.ProductDetailJson;
import com.sj.repository.model.ProductJson;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private EntityManager em;

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

		if (fc != null) {
			c.where(cb.equal(product.get("firstCategory"), fc));
			cq.where(cb.equal(product.get("firstCategory"), fc));
		}
		if (sc != null) {
			c.where(cb.equal(product.get("secondCategory"), sc));
			cq.where(cb.equal(product.get("secondCategory"), sc));
		}
		if (status != null) {
			c.where(cb.equal(product.get("status"), status));
			cq.where(cb.equal(product.get("status"), status));
		}
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
	public void updateState(Long id, ProductStatusEnum state) {
		em.createQuery("update Product p set p.status=:state where p.id=:id")
				.setParameter("state", state).setParameter("id", id)
				.executeUpdate();
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
}
