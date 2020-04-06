package com.pvthach.capstone.farming.repository;

import com.pvthach.capstone.farming.dto.DishDTO;
import com.pvthach.capstone.farming.model.Dish;
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
public class DishCustomRepositoryImpl implements DishCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<DishDTO>> searchDishes(Integer currentPage, Integer pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
        Root<Dish> root = criteria.from(Dish.class);


        List<Dish> result = new ArrayList<Dish>();
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
        Page<List<DishDTO>> page = new Page<List<DishDTO>>();
        List<DishDTO> dtos = convertToDTOs(result);
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(dtos);

        return page;
    }

    public long getCount(Integer currentPage, Integer pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Dish> root = criteria.from(Dish.class);

        criteria.select(builder.count(root));

        long count = entityManager.createQuery(criteria).getSingleResult();

        return count;
    }

    private List<DishDTO> convertToDTOs(List<Dish> list) {
        List<DishDTO> result = new ArrayList<DishDTO>();
        for (Dish dish : list) {
            result.add(dish.convertToDTO());
        }

        return result;
    }
}
