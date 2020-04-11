package com.pvthach.capstone.repository.order;

import com.pvthach.capstone.dto.OrderDTO;
import com.pvthach.capstone.dto.OrderSearchCriteria;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface OrderCustomRepository {

    Page<List<OrderDTO>> searchOrders(OrderSearchCriteria criteria);
}