package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by THACH-PC
 */

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}