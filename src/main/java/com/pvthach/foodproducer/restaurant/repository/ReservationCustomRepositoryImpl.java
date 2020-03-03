package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.dto.ReservationDTO;
import com.pvthach.foodproducer.restaurant.dto.ReservationSearchCriteria;
import com.pvthach.foodproducer.restaurant.model.Reservation;
import com.pvthach.foodproducer.restaurant.model.ReservationStaus;
import com.pvthach.foodproducer.service.Page;
import com.pvthach.foodproducer.ultil.DateFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<List<ReservationDTO>> searchReservations(ReservationSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
        Root<Reservation> root = criteria.from(Reservation.class);

        List<Predicate> conditions =  new ArrayList<Predicate>();

//        String from = criteriaSearch.getFrom();
//        String to = criteriaSearch.getTo();
//        if (from != null || to != null) {
//            if (from != null) {
//                Date fromDate = DateFormat.convertToDate(from);
//                conditions.add(builder.greaterThanOrEqualTo(root.get("from"), fromDate));
//            }
//            if (to != null) {
//                Date toDate = DateFormat.convertToDate(from);
//                conditions.add(builder.lessThanOrEqualTo(root.get("to"), toDate));
//            }
//
//        }

        String reservationId = criteriaSearch.getReservationId();
        if (reservationId != null && reservationId.length() > 0) {
            conditions.add(builder.equal(root.get("reservationId"), reservationId));
        }

        String status = criteriaSearch.getStatus();
        if (status != null && status.length() > 0) {
            conditions.add(builder.equal(root.get("status"), status));
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }
        criteria.orderBy(builder.desc(root.get("from")));


        List<Reservation> result = new ArrayList<Reservation>();

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
        Page<List<ReservationDTO>> page = new Page<List<ReservationDTO>>();
        List<ReservationDTO> dtos = convertToDTOs(result);
        page.setTotal(totalPage);
        page.setCurrent(currentPage);
        page.setData(dtos);

        return page;
    }

    public long getCount(ReservationSearchCriteria criteriaSearch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Reservation> root = criteria.from(Reservation.class);

        List<Predicate> conditions =  new ArrayList<Predicate>();

//        String from = criteriaSearch.getFrom();
//        String to = criteriaSearch.getTo();
//        if (from != null || to != null) {
//            if (from != null) {
//                Date fromDate = DateFormat.convertToDate(from);
//                conditions.add(builder.greaterThanOrEqualTo(root.get("from"), fromDate));
//            }
//            if (to != null) {
//                Date toDate = DateFormat.convertToDate(from);
//                conditions.add(builder.lessThanOrEqualTo(root.get("to"), toDate));
//            }
//
//        }

        String reservationId = criteriaSearch.getReservationId();
        if (reservationId != null && reservationId.length() > 0) {
            conditions.add(builder.equal(root.get("reservationId"), reservationId));
        }

        String status = criteriaSearch.getStatus();
        if (status != null && status.length() > 0) {
            conditions.add(builder.equal(root.get("status"), status));
        }

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }

        criteria.select(builder.count(root));

        long count = entityManager.createQuery(criteria).getSingleResult();

        return count;
    }

    @Override
    public List<Reservation> searchReservations(int year, int month, int day) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
        Root<Reservation> root = criteria.from(Reservation.class);

        List<Predicate> conditions =  new ArrayList<Predicate>();


        String from = year + "-" + month + "-" +  day + " 00:00:00";
        String to = year + "-" + month + "-" +  day + " 23:59:59";
        Date fromDate = DateFormat.convertToDate(from);
        conditions.add(builder.greaterThanOrEqualTo(root.get("from"), fromDate));
        Date toDate = DateFormat.convertToDate(to);
        conditions.add(builder.lessThanOrEqualTo(root.get("from"), toDate));
        conditions.add(builder.or(builder.equal(root.get("status"), ReservationStaus.PROCESSING.name()), builder.equal(root.get("status"), ReservationStaus.RESERVED.name())));

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }
        criteria.orderBy(builder.desc(root.get("from")));


        List<Reservation> result = new ArrayList<Reservation>();
        result = entityManager.createQuery(criteria).getResultList();

        return result;
    }

    private List<ReservationDTO> convertToDTOs (List<Reservation> list) {
        List<ReservationDTO> result = new ArrayList<ReservationDTO>();
        for (Reservation reservation : list) {
            result.add(reservation.convertToDTO());
        }

        return result;
    }
}
