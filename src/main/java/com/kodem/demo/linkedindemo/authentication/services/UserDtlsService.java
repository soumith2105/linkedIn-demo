package com.kodem.demo.linkedindemo.authentication.services;

import com.kodem.demo.linkedindemo.user.User;
import com.kodem.demo.linkedindemo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// This class will be called by SecurityConfig's configure method
@Service
public class UserDtlsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Get the user from UserRepository or throw an error
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		// Call UserDtls Constructor and return to the SecurityConfig
		return new UserDtls(user);
	}
}
