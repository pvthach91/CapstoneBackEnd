package com.pvthach.capstone.farming.repository;

import com.pvthach.capstone.farming.dto.OrderDTO;
import com.pvthach.capstone.farming.dto.OrderSearchCriteria;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface OrderCustomRepository {

    Page<List<OrderDTO>> searchOrders(OrderSearchCriteria criteria);
}