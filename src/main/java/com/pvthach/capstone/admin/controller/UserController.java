package com.pvthach.capstone.admin.controller;

import com.pvthach.capstone.message.request.ChangePasswordForm;
import com.pvthach.capstone.message.request.SignUpForm;
import com.pvthach.capstone.message.response.*;
import com.pvthach.capstone.admin.model.Role;
import com.pvthach.capstone.admin.model.RoleName;
import com.pvthach.capstone.admin.model.User;
import com.pvthach.capstone.admin.repository.RoleRepository;
import com.pvthach.capstone.admin.repository.UserRepository;
import com.pvthach.capstone.service.Page;
import com.pvthach.capstone.service.UserCriteriaSearch;
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
	
//	@GetMapping("/api/users")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<User> getUsers() {
//		return userRepository.findAll();
//	}

	@PostMapping("/api/users")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<List<User>> getUsers(@RequestBody UserCriteriaSearch criteria) {
		return userRepository.searchUsers(criteria);
	}

	@GetMapping("/api/currentUser")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PM')")
	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		return savedUser;
	}

	@GetMapping("/api/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUser(@PathVariable Long id) {

		User savedUser = userRepository.findById(id).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		return savedUser;
	}

	@PostMapping("/api/user/promoteToAdmin/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<User> promoteToAdmin(@PathVariable String username) {

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		Set<Role> roles = new HashSet<Role>();

		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException(EnumResponse.ROLE_NOT_FOUND.getDescription()));
		roles.add(userRole);

		savedUser.setRoles(roles);
		User u = userRepository.save(savedUser);

		return Response.successResult(u);
	}

	@PostMapping("/api/user/deactivate/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<User> deactivateUser(@PathVariable String username) {

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		savedUser.setActive(false);
		User u = userRepository.save(savedUser);

		return Response.successResult(u);
	}

	@PostMapping("/api/user/activate/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<User> activateUser(@PathVariable String username) {

		User savedUser = userRepository.findByUsername(username).
				orElseThrow(() -> new RuntimeException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		savedUser.setActive(true);
		User u = userRepository.save(savedUser);

		return Response.successResult(u);
	}

	@PostMapping("/changePassword")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PM')")
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

	@PostMapping("/api/user/createHRUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createHRUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_HR);
	}

	@PostMapping("/api/user/createAccountingUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createAccountingUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_ACCOUNTING);
	}

	@PostMapping("/api/user/createStockUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createStockUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_STOCK);
	}

	@PostMapping("/api/user/createSaleUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createSaleUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_SALE);
	}

	@PostMapping("/api/user/createBODUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<String> createBODUser(@RequestBody SignUpForm signUpRequest) {
		return createUser(signUpRequest, RoleName.ROLE_BOD);
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
