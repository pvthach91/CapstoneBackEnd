package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.dto.OrderDTO;
import com.pvthach.foodproducer.restaurant.dto.OrderSearchCriteria;
import com.pvthach.foodproducer.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface OrderCustomRepository {

    Page<List<OrderDTO>> searchOrders(OrderSearchCriteria criteria);
}