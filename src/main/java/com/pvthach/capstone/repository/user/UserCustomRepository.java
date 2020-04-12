package com.pvthach.capstone.repository.user;

import com.pvthach.capstone.dto.UserDTO;
import com.pvthach.capstone.service.Page;
import com.pvthach.capstone.dto.UserCriteriaSearch;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface UserCustomRepository {

    Page<List<UserDTO>> searchUsers(UserCriteriaSearch criteriaSearch);
}