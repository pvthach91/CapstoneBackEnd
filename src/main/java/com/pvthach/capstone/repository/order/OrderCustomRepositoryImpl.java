package com.pvthach.capstone.repository.order;

import com.pvthach.capstone.dto.OrderDTO;
import com.pvthach.capstone.dto.OrderSearchCriteria;
import com.pvthach.capstone.model.OrderItem;
import com.pvthach.capstone.model.Ordering;
import com.pvthach.capstone.model.Product;
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
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<OrderDTO>> searchOrders(OrderSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ordering> criteria = builder.createQuery(Ordering.class);
        Root<Ordering> root = criteria.from(Ordering.class);

        Join<Ordering, User> user = root.join("orderBy");

        String farmerName = criteriaSearch.getFarmer();
        Join<Product, User> farmer = null;
        if (farmerName != null && farmerName.length()> 0) {
            Join<Ordering, OrderItem> items = root.join("items");
            Join<OrderItem, Product> product = items.join("product");
            farmer = product.join("user");
            criteria.distinct(true);
        }

        List<Predicate> conditions =  new ArrayList<Predicate>();

        String orderId = criteriaSearch.getOrderId();
        if (orderId != null && orderId.length() > 0) {
            conditions.add(builder.equal(root.get("orderId"), orderId));
        }

        String status = criteriaSearch.getStatus();
        if (status != null && status.length() > 0) {
            conditions.add(builder.equal(root.get("status"), status));
        }

        String orderBy = criteriaSearch.getOrderBy();
        if (orderBy != null && orderBy.length() > 0) {
            conditions.add(builder.equal(user.get("username"), orderBy));
        }

        if (farmerName != null && farmerName.length()> 0) {
            conditions.add(builder.equal(farmer.get("username"), farmerName));
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }
        criteria.orderBy(builder.desc(root.get("date")));


        List<Ordering> result = new ArrayList<Ordering>();

        long count = getCount(criteriaSearch);
        Integer pageSize = criteriaSearch.getPageSize();
        Integer currentPage = criteriaSearch.getCurrentPage();

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
        Page<List<OrderDTO>> page = new Page<List<OrderDTO>>();
        List<OrderDTO> dtos = Ordering.convertToDTOs(result);
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(dtos);

        return page;
    }

    public long getCount(OrderSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Ordering> root = criteria.from(Ordering.class);

        Join<Ordering, User> user = root.join("orderBy");

        String farmerName = criteriaSearch.getFarmer();
        Join<Product, User> farmer = null;
        if (farmerName != null && farmerName.length()> 0) {
            Join<Ordering, OrderItem> items = root.join("items");
            Join<OrderItem, Product> product = items.join("product");
            farmer = product.join("user");
            criteria.distinct(true);
        }

        List<Predicate> conditions =  new ArrayList<Predicate>();

        String orderId = criteriaSearch.getOrderId();
        if (orderId != null && orderId.length() > 0) {
            conditions.add(builder.equal(root.get("orderId"), orderId));
        }

        String status = criteriaSearch.getStatus();
        if (status != null && status.length() > 0) {
            conditions.add(builder.equal(root.get("status"), status));
        }

        String orderBy = criteriaSearch.getOrderBy();
        if (orderBy != null && orderBy.length() > 0) {
            conditions.add(builder.equal(user.get("username"), orderBy));
        }

        if (farmerName != null && farmerName.length()> 0) {
            conditions.add(builder.equal(farmer.get("username"), farmerName));
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        criteria.select(builder.count(root));

        long count = entityManager.createQuery(criteria).getSingleResult();

        return count;
    }

}
