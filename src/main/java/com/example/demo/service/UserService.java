package com.example.demo.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.base.ExceptionController;
import com.example.demo.exception.SimplesConstant;
import com.example.demo.model.Role;
import com.example.demo.model.UserModel;
import com.example.demo.pojo.LoginRequest;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.util.JwtUtils;

@Service
public class UserService extends ExceptionController{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserRepo userRepo;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepo roleRepo;

	public ResponseEntity<Object> signin(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UserModel user = userRepo.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("not found"));
		user.setToken(jwt);
		return response(HttpStatus.OK.value(), user);
	}
	
	public ResponseEntity<Object> signUp(UserModel userModel){
		UserModel userDetails = new UserModel();
		boolean emailExists = userRepo.existsByEmail(userModel.getEmail());
		if(emailExists) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),"Email ID already exists");
		}
			String enocode = encoder.encode(userModel.getPassword());
			userDetails.setPassword(enocode);
			userDetails.setUsername(userModel.getUsername());
			userDetails.setConfirmPassword(userModel.getConfirmPassword());
			userDetails.setPhone(userModel.getPhone());
			userDetails.setEmail(userModel.getEmail());
			Set<Role> role = roleRepo.findByRolename("user");
			userDetails.setRoles(role);
			userRepo.save(userDetails);
			mailSender(userDetails.getEmail());
			return response(HttpStatus.OK.value(),"user created successfully", userDetails);		
	}
	
	public void mailSender(String emailId) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("shaping <kprabha10192@gamil.com>");
		email.setSubject("welcome to shaping word");
		email.setText("");
		email.setTo(emailId);
		mailSender.send(email);
	}
}
