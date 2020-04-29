package com.pvthach.capstone.controller;

import com.pvthach.capstone.message.request.LoginForm;
import com.pvthach.capstone.message.request.SignUpForm;
import com.pvthach.capstone.message.response.*;
import com.pvthach.capstone.model.Role;
import com.pvthach.capstone.model.RoleName;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.user.RoleRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import com.pvthach.capstone.security.jwt.JwtProvider;
import com.pvthach.capstone.security.servies.UserPrinciple;
import com.pvthach.capstone.service.MailService;
import com.pvthach.capstone.ultil.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private int USERNAME_MAX = 50;
	private int EMAIL_MAX = 50;
	private int PASSWORD_MAX = 100;

	private int USERNAME_MIN = 3;
	private int EMAIL_MIN = 3;
	private int PASSWORD_MIN = 3;

	private int PHONE_MAX = 14;

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	MailService mailService;

	@PostMapping("/signin")
	public ApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		try{
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtProvider.generateJwtToken(authentication);
//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

			if (!userDetails.isActive()) {
				return Response.failedResult("Your account is inactive, please contact us to activate your account");
			}
			return Response.successResult(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getName(), userDetails.getAuthorities()));
		} catch (AuthenticationException e) {
			return Response.failedResult("Please check your username and password");
		}
	}

	@PostMapping("/signup")
	public ApiResponse<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
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

		if (signUpRequest.getRole() == null) {
			return Response.failedResult("No role");
		}
		RoleName role = RoleName.ROLE_FARMER;
		if (signUpRequest.getRole() == RoleUtil.ROLE_BUYER) {
			role = RoleName.ROLE_BUYER;
		} else if (signUpRequest.getRole() == RoleUtil.ROLE_DRIVER) {
			role = RoleName.ROLE_DRIVER;
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), true);

		user.setAddress(signUpRequest.getAddress());
		user.setPhoto(signUpRequest.getPhoto());
		user.setLatitude(signUpRequest.getLatitude());
		user.setLongitude(signUpRequest.getLongitude());
		user.setState(signUpRequest.getState());

		Set<Role> roles = new HashSet<>();


		Role userRole = roleRepository.findByName(role)
				.orElseThrow(() -> new RuntimeException(EnumResponse.ROLE_NOT_FOUND.getDescription()));
		roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);

		return Response.successResult(EnumResponse.USER_REGISTERED_SUCCESS.getDescription());
	}
}
