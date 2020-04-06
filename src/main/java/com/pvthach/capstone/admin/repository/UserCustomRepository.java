package com.pvthach.capstone.admin.repository;

import com.pvthach.capstone.admin.model.User;
import com.pvthach.capstone.service.Page;
import com.pvthach.capstone.service.UserCriteriaSearch;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface UserCustomRepository {

    Page<List<User>> searchUsers(UserCriteriaSearch criteriaSearch);
}