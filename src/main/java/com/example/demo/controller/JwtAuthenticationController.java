//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.base.ExceptionController;
//import com.example.demo.basemodel.Response;
//import com.example.demo.configuration.JwtTokenUtil;
//import com.example.demo.model.UserModel;
//import com.example.demo.repo.UserRepo;
//import com.example.demo.service.JwtUserDetailsService;
//
//
//
//@RestController
//public class JwtAuthenticationController extends ExceptionController {
//	
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//	@Autowired 
//	private JwtUserDetailsService userDetailsService;
//	@Autowired
//	UserRepo repo;
//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
//	
//	@RequestMapping(value = "/main/login", method = RequestMethod.POST)
//	public Response createAuthenticationToken(@RequestBody UserModel authenticationRequest) throws Exception {
//		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//		final String token = jwtTokenUtil.generateToken(userDetails);
//		UserModel users=repo.findByEmail(authenticationRequest.getEmail());
//		users.setToken(token);
//		return response("Logged on successfully", users);
//	}
//}
//	
