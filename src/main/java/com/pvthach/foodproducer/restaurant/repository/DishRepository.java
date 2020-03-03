package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, DishCustomRepository {

    List<Dish> findTop4By();
}