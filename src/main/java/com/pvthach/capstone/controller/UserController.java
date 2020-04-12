package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.UserDTO;
import com.pvthach.capstone.message.request.ChangePasswordForm;
import com.pvthach.capstone.message.request.SignUpForm;
import com.pvthach.capstone.message.response.*;
import com.pvthach.capstone.model.Role;
import com.pvthach.capstone.model.RoleName;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.user.RoleRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import com.pvthach.capstone.service.Page;
import com.pvthach.capstone.dto.UserCriteriaSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	private int USERNAME_MAX = 50;
	private int EMAIL_MAX = 50;
	private int PASSWORD_MAX = 100;

	private int USERNAME_MIN = 3;
	private int EMAIL_MIN = 3;
	private int PASSWORD_MIN = 3;

	private int PHONE_MAX = 14;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping("/api/users")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<List<UserDTO>> getUsers(@RequestBody UserCriteriaSearch criteria) {
		return userRepository.searchUsers(criteria);
	}

	@GetMapping("/api/currentUser")
	@PreAuthorize("hasRole('FARMER') or hasRole('ADMIN') or hasRole('PM') or hasRole('BUYER') or hasRole('DRIVER')")
	public UserDTO getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		UserDTO dto = savedUser.convertToDTO();

		return dto;
	}

	@GetMapping("/api/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public UserDTO getUser(@PathVariable Long id) {

		User savedUser = userRepository.findById(id).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		UserDTO dto = savedUser.convertToDTO();

		return dto;
	}

	@PostMapping("/api/user/deactivate/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<Long> deactivateUser(@PathVariable String username) {

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		savedUser.setActive(false);
		User u = userRepository.save(savedUser);

		return Response.successResult(u.getId());
	}

	@PostMapping("/api/user/activate/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<Long> activateUser(@PathVariable String username) {

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		savedUser.setActive(true);
		User u = userRepository.save(savedUser);

		return Response.successResult(u.getId());
	}

	@PostMapping("/changePassword")
	@PreAuthorize("hasRole('FARMER') or hasRole('ADMIN') or hasRole('PM') or hasRole('BUYER') or hasRole('DRIVER')")
	public ApiResponse<String> changePassword(@Valid @RequestBody ChangePasswordForm form) {
		String oldPassword = form.getOldPassword();
		String newPassword = form.getNewPassword();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		if (StringUtils.isEmpty(oldPassword) || oldPassword.length() < PASSWORD_MIN || oldPassword.length() > PASSWORD_MAX ) {
			return Response.failedResult(EnumResponse.PASSWORD_INVALID.getDescription());
		}

		if (StringUtils.isEmpty(newPassword) || newPassword.length() < PASSWORD_MIN || newPassword.length() > PASSWORD_MAX ) {
			return Response.failedResult(EnumResponse.PASSWORD_INVALID.getDescription());
		}
		if (!savedUser.getPassword().equals(encoder.encode(oldPassword)) ) {
			return Response.failedResult("Old password is not correct");
		}
		savedUser.setPassword(encoder.encode(newPassword));
		userRepository.save(savedUser);

		return Response.successResult("Password changed successfully");
	}

	@PostMapping("/api/user/createOperation")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createHRUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_PM);
	}

	public ApiResponse<String> createUser(SignUpForm signUpRequest, RoleName roleName) {
		// Validate username
		String username = signUpRequest.getUsername();
		if (StringUtils.isEmpty(username) || username.length() < USERNAME_MIN || username.length() > USERNAME_MAX ) {
			return Response.failedResult(EnumResponse.USERNAME_INVALID.getDescription());
		}
		if (userRepository.existsByUsername(username)) {
			return Response.failedResult(EnumResponse.USERNAME_EXIST.getDescription());
		}

		// Validate email
		String email = signUpRequest.getEmail();
		if (StringUtils.isEmpty(email) || email.length() < EMAIL_MIN || email.length() > EMAIL_MAX ) {
			return Response.failedResult(EnumResponse.EMAIL_INVALID.getDescription());
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return Response.failedResult(EnumResponse.EMAIL_EXIST.getDescription());
		}

		// Valaidate phone
		String phone = signUpRequest.getPhone();
		if (StringUtils.isEmpty(phone) || phone.length() < 1 || phone.length() > PHONE_MAX ) {
			return Response.failedResult(EnumResponse.PHONE_INVALID.getDescription());
		}

		// Validate password
		String password = signUpRequest.getPassword();
		if (StringUtils.isEmpty(password) || password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX ) {
			return Response.failedResult(EnumResponse.PASSWORD_INVALID.getDescription());
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), true);

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(roleName)
				.orElseThrow(() -> new RuntimeException(EnumResponse.ROLE_NOT_FOUND.getDescription()));
		roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);

		return Response.successResult(EnumResponse.USER_REGISTERED_SUCCESS.getDescription());
	}
}
