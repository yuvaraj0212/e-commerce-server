package com.example.demo.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.ExceptionController;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.SimplesConstant;
import com.example.demo.model.UserModel;
import com.example.demo.pojo.LoginRequest;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@RestController
public class UserController extends ExceptionController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserService userService;
	
	@PostMapping(value="/signin")
	public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.signin(loginRequest);
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel userModel){
		return userService.signUp(userModel);
	}	
	
	@GetMapping(value="/user-list")
	public ResponseEntity<Object> getUserList(){
		return userService.getUserList();
	}
	
	@GetMapping(value="/current-user")
	public ResponseEntity<Object> getCurrentUser(){
		return userService.getCurrentUser();
	}

}
