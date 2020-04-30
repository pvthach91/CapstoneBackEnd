package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.Address;
import com.pvthach.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUser(User user);
}