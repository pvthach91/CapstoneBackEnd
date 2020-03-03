package com.pvthach.capstone.restaurant.repository;

import com.pvthach.capstone.restaurant.dto.OrderDTO;
import com.pvthach.capstone.restaurant.dto.OrderSearchCriteria;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface OrderCustomRepository {

    Page<List<OrderDTO>> searchOrders(OrderSearchCriteria criteria);
}