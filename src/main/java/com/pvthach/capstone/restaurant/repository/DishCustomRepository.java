package com.pvthach.capstone.restaurant.repository;

import com.pvthach.capstone.restaurant.dto.DishDTO;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface DishCustomRepository {

    Page<List<DishDTO>> searchDishes(Integer currentPage, Integer pageSize);
}