package com.pvthach.capstone.repository.product;

import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {

    List<Product> findTop8By();

    List<Product> findAllByUser(User user);

    List<Product> findAllByStatus(String status);

    List<Product> findAllByUserAndStatus(User user, String status);
}