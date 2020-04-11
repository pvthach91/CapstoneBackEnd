package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.Farm;
import com.pvthach.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findAllByUser(User user);
}