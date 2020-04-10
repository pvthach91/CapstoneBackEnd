package com.pvthach.capstone.repository.user;

import com.pvthach.capstone.model.Role;
import com.pvthach.capstone.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by THACH-PC
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}