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
	
	@PostMapping("/signin")
	public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.signin(loginRequest);
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel userModel){
		return userService.signUp(userModel);
	}	
	

	@RequestMapping(value = "/getuser", method = { RequestMethod.GET })
	@ResponseBody
	public UserModel getuser(@RequestParam(name = "id") String id) {
		return userRepo.findById(Long.parseLong(id)).get();
	}

	@RequestMapping(value = "/deleteusers", method = { RequestMethod.DELETE })
	@ResponseBody
	public String delete(@RequestParam(name = "id") String id) {
		userRepo.delete(userRepo.findById(Long.parseLong(id)).get());
		return "dashboard";
	}

	@RequestMapping(value = "/getlist/user", method = { RequestMethod.GET })
	public List<UserModel> getAllUser(HttpServletResponse http) {

		List<UserModel> getListUser = userRepo.findAll();
		// Collections.reverse(getListUser);
		// getListUser.forEach(action-> {

		// } );
		return getListUser;

	}

	@RequestMapping(value = "/datalist/orderrequest", method = RequestMethod.GET)
	public List<UserModel> Orderdetails(
			HttpServletResponse response1/* , @PageableDefault(size = 7) Pageable pageable */) {
		List<UserModel> orderdetails = new ArrayList<>();
		orderdetails = userRepo.findAll();
		Collections.reverse(orderdetails);
		orderdetails.forEach(action -> {
		});
		// response1.setHeader("Content-Range", "users 0-" + productRepo.count() + "/" +
		// productRepo.count());
		return orderdetails;

	}

//	@RequestMapping(value = "/edit/user", method = { RequestMethod.POST })
//
//	public Response editUser(@RequestBody UserModel user) throws IOException, BaseException {
//		UserModel user1 = userRepo.findById(user.getId()).get();
//		// if (userRepo.findByUserName(user.getUserName()) == null) {
//		if (userRepo.findByEmail(user.getEmail()) == null) {
//			user1.setPhone(user.getPhone());
//			user1.setEmail(user.getEmail());
////			user1.setUserName(user.getRolename());
//			userRepo.save(user1);
//			return response("Edit Succcessfully", user1);
//
//		}
//		return failure(SimplesConstant.CODE_0.getCode(), "Mail Id Already Exists");
//
//	}
	// return failure(SimplesConstant.CODE_0.getCode(), "Mail Id Already Exists");

}
