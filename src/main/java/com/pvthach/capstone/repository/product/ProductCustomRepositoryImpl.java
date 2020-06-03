package com.pvthach.capstone.repository.product;

import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.ProductSearchCriteria;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.ProductStatus;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.service.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

//    @Override
//    public Page<List<ProductDTO>> searchProducts(Integer currentPage, Integer pageSize) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
//        Root<Product> root = criteria.from(Product.class);
//
//
//        List<Product> result = new ArrayList<Product>();
//        criteria.orderBy(builder.desc(root.get("dateCreated")));
//
//        long count = getCount(currentPage, pageSize);
//
//        long totalPage = 1;
//        if (count % pageSize == 0) {
//            totalPage = count / pageSize;
//        } else {
//            totalPage = count / pageSize + 1;
//        }
//        if (totalPage < 1) {
//            currentPage = 0;
//        } else {
//            int start = pageSize * (currentPage - 1);
//            result = entityManager.createQuery(criteria).setFirstResult(start).setMaxResults(pageSize).getResultList();
//        }
//        Page<List<ProductDTO>> page = new Page<List<ProductDTO>>();
//        List<ProductDTO> dtos = Product.convertToDTOs(result);
//        page.setTotal(totalPage);
//        page.setCurrent(currentPage);
//        page.setData(dtos);
//
//        return page;
//    }

    @Override
    public List<Product> searchProducts(ProductSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);


        List<Predicate> conditions =  new ArrayList<Predicate>();

        conditions.add(builder.equal(root.get("status"), ProductStatus.APPROVED.name()));

        String state = criteriaSearch.getState();
        if (state != null && state.length() > 0) {
            Join<Product, User> user = root.join("user");
            conditions.add(builder.equal(user.get("state"), state));
        }

        String name = criteriaSearch.getName();
        if (name != null && name.length() > 0) {
            conditions.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        List<String> category = criteriaSearch.getCategory();
        if (category != null && category.size() > 0) {
            conditions.add(root.get("category").in(category));
        }

        Boolean promotionActive = criteriaSearch.getPromotionActive();
        if (promotionActive != null) {
            conditions.add(builder.equal(root.get("promotionActive"), promotionActive));
        }

        Integer priceFrom = criteriaSearch.getPriceFrom();
        Integer priceTo = criteriaSearch.getPriceTo();
        if (priceFrom != null || priceTo != null) {
            if (priceFrom != null) {
                conditions.add(builder.greaterThanOrEqualTo(root.get("promotionPrice"), priceFrom));
            }
            if (priceTo != null) {
                conditions.add(builder.lessThanOrEqualTo(root.get("promotionPrice"), priceTo));
            }

        }

        if (criteriaSearch.getSort() == null || criteriaSearch.getSort()== 0) {
            // Default search by newness
            criteria.orderBy(builder.desc(root.get("dateCreated")));
        } else if (criteriaSearch.getSort()== 1) {
            // Sort By Price: Low to High
            criteria.orderBy(builder.asc(root.get("promotionPrice")));
        } else if (criteriaSearch.getSort()== 2) {
            //Sort By Price: High to Low
            criteria.orderBy(builder.desc(root.get("promotionPrice")));
        } else if (criteriaSearch.getSort()== 3) {
            //Sort By Nearest: Do in front end
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        List<Product> result = entityManager.createQuery(criteria).getResultList();

        return result;
    }

//    public long getCount(Integer currentPage, Integer pageSize) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
//        Root<Product> root = criteria.from(Product.class);
//
//        criteria.select(builder.count(root));
//
//        long count = entityManager.createQuery(criteria).getSingleResult();
//
//        return count;
//    }
}
