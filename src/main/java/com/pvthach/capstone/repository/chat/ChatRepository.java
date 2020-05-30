package com.pvthach.capstone.repository.chat;

import com.pvthach.capstone.model.Chat;
import com.pvthach.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>, ChatCustomRepository {

    List<Chat> findAllByFromUserOrToUserOrderByDateCreatedDesc(User from, User to);

    List<Chat> findAllByFromUserAndToUserOrderByDateCreatedDesc(User from, User to);

    List<Chat> findTop100ByFromUserAndToUserOrderByDateCreatedDesc(User from, User to);
}