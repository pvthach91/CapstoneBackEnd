package com.pvthach.capstone.repository.chat;

import com.pvthach.capstone.dto.ChatDTO;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface ChatCustomRepository {

    List<ChatDTO> getChats(String fromUser, String toUser);
}