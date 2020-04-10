package com.pvthach.capstone.repository.user;

import com.pvthach.capstone.model.Role;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.service.Page;
import com.pvthach.capstone.service.UserCriteriaSearch;
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
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<User>> searchUsers(UserCriteriaSearch criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

        Join<User, Role> joinRole = user.join("roles");
        List<Predicate> conditions =  new ArrayList<Predicate>();

        String username = criteriaSearch.getUsername();
        if (username != null && username.trim().length() > 0) {
            username = username.trim();
            conditions.add(builder.equal(user.get("username"), username));
        }

        Boolean isActive = criteriaSearch.getIsActive();
        if (isActive != null) {
            conditions.add(builder.equal(user.get("isActive"), isActive));
        }


        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        if (criteriaSearch.getSort() == null || criteriaSearch.getSort()== 0) {
//            criteria.orderBy(builder.desc(user.get("date")));
        } else if (criteriaSearch.getSort()== 1) {
            criteria.orderBy(builder.asc(user.get("username")));
        } else if (criteriaSearch.getSort()== 2) {
            criteria.orderBy(builder.desc(user.get("username")));
        }

        List<User> result = new ArrayList<User>();

        List<User> all = new ArrayList<User>();
        all = entityManager.createQuery(criteria).getResultList();
        List<User> subAll = new ArrayList<User>();
        Long role = criteriaSearch.getRole();
        if (role != null && role != 0) {
            for (User u : all) {
                if (u.containRole(role)) {
                    subAll.add(u);
                }
            }
        } else {
            subAll= all;
        }

        int count = subAll.size();

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
            int end = count;
            if (count >= (start + pageSize)) {
                end = (start + pageSize);
            }
            result = subAll.subList(start, end);
        }
        Page<List<User>> page = new Page<List<User>>();
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(result);

        return page;

//        List<User> result = entityManager.createQuery(criteria).getResultList();
//
//        return result;
    }

    public long getCount(UserCriteriaSearch criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<User> user = criteria.from(User.class);

        Join<User, Role> joinRole = user.join("roles");
        List<Predicate> conditions =  new ArrayList<Predicate>();

        String username = criteriaSearch.getUsername();
        if (username != null && username.trim().length() > 0) {
            username = username.trim();
            conditions.add(builder.equal(user.get("username"), username));
        }

        Boolean isActive = criteriaSearch.getIsActive();
        if (isActive != null) {
            conditions.add(builder.equal(user.get("isActive"), isActive));
        }

//        Long role = criteriaSearch.getRole();
//        if (role != null && role != 0) {
//            String roleName = RoleName.ROLE_FARMER.name();
//            if (role == RoleUtil.ROLE_BUYER.longValue()) {
//                roleName = RoleName.ROLE_BUYER.name();
//            } else if (role == 3) {
//                roleName = RoleName.ROLE_ADMIN.name();
//            }
//            conditions.add(builder.equal(joinRole.get("name"), roleName));
//        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        criteria.select(builder.count(user));

        long count = entityManager.createQuery(criteria).getSingleResult();

        return count;
    }
}