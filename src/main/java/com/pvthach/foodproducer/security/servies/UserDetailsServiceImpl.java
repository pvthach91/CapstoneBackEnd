package com.pvthach.foodproducer.security.servies;

import com.pvthach.foodproducer.message.response.EnumResponse;
import com.pvthach.foodproducer.model.User;
import com.pvthach.foodproducer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by THACH-PC
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));

		return UserPrinciple.build(user);
	}
}