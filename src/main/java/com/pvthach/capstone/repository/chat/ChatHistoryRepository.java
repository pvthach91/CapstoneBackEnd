package com.pvthach.capstone.repository.chat;

import com.pvthach.capstone.model.ChatHistory;
import com.pvthach.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    List<ChatHistory> findAllByFromUserOrToUserOrderByLastUpdatedDesc(User fromUser, User toUSer);

    List<ChatHistory> findAllByFromUserAndToUser(User fromUser, User toUSer);

}