package com.pvthach.capstone.repository.shippingConfig;

import com.pvthach.capstone.dto.ShippingConfigDTO;
import com.pvthach.capstone.dto.ShippingConfigSearchCriteria;
import com.pvthach.capstone.model.ShippingConfig;
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
public class ShippingConfigCustomRepositoryImpl implements ShippingConfigCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<ShippingConfigDTO>> searchShippingConfigs(ShippingConfigSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShippingConfig> criteria = builder.createQuery(ShippingConfig.class);
        Root<ShippingConfig> root = criteria.from(ShippingConfig.class);

        List<Predicate> conditions =  new ArrayList<Predicate>();

        String state = criteriaSearch.getState();
        if (state != null && state.length() > 0) {
            conditions.add(builder.equal(root.get("state"), state));
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        List<ShippingConfig> result = new ArrayList<ShippingConfig>();

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
        Page<List<ShippingConfigDTO>> page = new Page<List<ShippingConfigDTO>>();
        List<ShippingConfigDTO> dtos = ShippingConfig.convertToDTOs(result);
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(dtos);

        return page;
    }

    public long getCount(ShippingConfigSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ShippingConfig> root = criteria.from(ShippingConfig.class);

        List<Predicate> conditions =  new ArrayList<Predicate>();

        String state = criteriaSearch.getState();
        if (state != null && state.length() > 0) {
            conditions.add(builder.equal(root.get("state"), state));
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
