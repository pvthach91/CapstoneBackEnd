package com.pvthach.capstone.repository.product;

import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.ProductSearchCriteria;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.service.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<ProductDTO>> searchProducts(Integer currentPage, Integer pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);


        List<Product> result = new ArrayList<Product>();
        criteria.orderBy(builder.desc(root.get("dateCreated")));

        long count = getCount(currentPage, pageSize);

        long totalPage = 1;
        if (count % pageSize == 0) {
            totalPage = count / pageSize;
        } else {
            totalPage = count / pageSize + 1;
        }
        if (totalPage < 1) {
            currentPage = 0;
        } else {
            int start = pageSize * (currentPage - 1);
            result = entityManager.createQuery(criteria).setFirstResult(start).setMaxResults(pageSize).getResultList();
        }
        Page<List<ProductDTO>> page = new Page<List<ProductDTO>>();
        List<ProductDTO> dtos = Product.convertToDTOs(result);
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(dtos);

        return page;
    }

    @Override
    public List<Product> searchProducts(ProductSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);


        List<Predicate> conditions =  new ArrayList<Predicate>();

        String name = criteriaSearch.getName();
        if (name != null && name.length() > 0) {
            conditions.add(builder.equal(root.get("name"), name));
        }

        String category = criteriaSearch.getCategory();
        if (category != null && category.length() > 0) {
            conditions.add(builder.equal(root.get("category"), category));
        }

        Boolean promotionActive = criteriaSearch.getPromotionActive();
        if (promotionActive != null) {
            conditions.add(builder.equal(root.get("promotionActive"), promotionActive));
        }

        Integer priceFrom = criteriaSearch.getPriceFrom();
        Integer priceTo = criteriaSearch.getPriceTo();
        if (priceFrom != null || priceTo != null) {
            if (priceFrom != null) {
                conditions.add(builder.greaterThanOrEqualTo(root.get("price"), priceFrom));
            }
            if (priceTo != null) {
                conditions.add(builder.lessThanOrEqualTo(root.get("price"), priceTo));
            }

        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        List<Product> result = entityManager.createQuery(criteria).getResultList();

        return result;
    }

    public long getCount(Integer currentPage, Integer pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Product> root = criteria.from(Product.class);

        criteria.select(builder.count(root));

        long count = entityManager.createQuery(criteria).getSingleResult();

        return count;
    }
}
