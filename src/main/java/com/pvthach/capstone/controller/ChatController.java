package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.ChatDTO;
import com.pvthach.capstone.dto.UserDTO;
import com.pvthach.capstone.dto.request.ChatRequestDTO;
import com.pvthach.capstone.model.Chat;
import com.pvthach.capstone.model.ChatHistory;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.ChatHistoryRepository;
import com.pvthach.capstone.repository.ChatRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ChatController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ChatRepository chatRepository;

	@Autowired
	ChatHistoryRepository chatHistoryRepository;

	@GetMapping("/api/chats/{id}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public List<ChatDTO> getChats(@PathVariable Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User fromUser = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		User toUser = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Chat> chats = chatRepository.findTop100ByFromUserAndToUserOrderByDateCreatedDesc(fromUser, toUser);
		return Chat.convertToDTOs(chats);
	}

	@GetMapping("/api/chats")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public List<UserDTO> getChatContact() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<ChatHistory> chats = chatHistoryRepository.findAllByFromUserOrToUser(user, user);
		List<User> contacts = new ArrayList<User>();
		for (ChatHistory chat: chats) {
			User fromUser = chat.getFromUser();
			User toUser = chat.getToUser();
			if (fromUser.equals(user)) {
				if (!contacts.contains(toUser)) {
					contacts.add(toUser);
				}
			} else {
				if (!contacts.contains(fromUser)) {
					contacts.add(fromUser);
				}
			}
		}

		List<UserDTO> result = User.convertToDTOs(contacts);
		return result;
	}

	@PostMapping("/api/chat/addChat}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public Long addChat(@RequestBody ChatRequestDTO dto) {
		Chat chat = new Chat();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User fromUser = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		User toUser = userRepository.findById(dto.getToUser()).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<ChatHistory> chatHistories1 = chatHistoryRepository.findAllByFromUserAndToUser(fromUser, toUser);
		List<ChatHistory> chatHistories2 = chatHistoryRepository.findAllByFromUserAndToUser(toUser, fromUser);
		ChatHistory chatHistory = new ChatHistory();
		if (chatHistories1.size() == 0 && chatHistories2.size() == 0) {
			chatHistory.setFromUser(fromUser);
			chatHistory.setToUser(toUser);
		} else {
			if (chatHistories1.size() > 0) {
				chatHistory = chatHistories1.get(0);
			} else {
				chatHistory = chatHistories2.get(0);
			}
			chatHistory.setLastUpdated(new Date());
		}

		chat.setContent(dto.getContent());
		chat.setFromUser(fromUser);
		chat.setToUser(toUser);
		chat.setSourceVisible(true);
		chat.setDestinationVisible(true);

		Chat savedChat = chatRepository.save(chat);
		ChatHistory ch = chatHistoryRepository.save(chatHistory);
		return savedChat.getId();
	}
}
