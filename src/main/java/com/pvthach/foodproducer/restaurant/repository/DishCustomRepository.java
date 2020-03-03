package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.dto.DishDTO;
import com.pvthach.foodproducer.restaurant.model.Dish;
import com.pvthach.foodproducer.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface DishCustomRepository {

    Page<List<DishDTO>> searchDishes(Integer currentPage, Integer pageSize);
}