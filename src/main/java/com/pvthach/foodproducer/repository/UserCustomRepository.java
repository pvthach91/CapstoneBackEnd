package com.pvthach.foodproducer.repository;

import com.pvthach.foodproducer.model.User;
import com.pvthach.foodproducer.service.Page;
import com.pvthach.foodproducer.service.UserCriteriaSearch;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface UserCustomRepository {

    Page<List<User>> searchUsers(UserCriteriaSearch criteriaSearch);
}