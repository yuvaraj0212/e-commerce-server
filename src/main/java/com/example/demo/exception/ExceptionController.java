//package com.example.demo.exception;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//import com.example.demo.pojo.MessageResponse;
//
//
//
//
//@Component
//@ControllerAdvice
//public abstract class ExceptionController {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
//
//             
//
//	/** These methods returns the response value. */
//	protected MessageResponse response() {
//		return new MessageResponse();
//	}
//
//	protected ResponseEntity<Object> response(String message) {
//		MessageResponse response = new MessageResponse();
//		response.setMessage(message);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(int status, Object result) {
//		MessageResponse response = new MessageResponse();
//		response.setStatus(status);
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(int status, Map<String, Object> result) {
//		MessageResponse response = new MessageResponse();
//		response.setStatus(status);
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(int status, String message, List<Object> result) {
//		MessageResponse response = new MessageResponse();
//		response.setStatus(status);
//		response.setMessage(message);
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(int status, String message) {
//		MessageResponse response = new MessageResponse();
//		response.setStatus(status);
//		response.setMessage(message);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(int status, String message, Object result) {
//		MessageResponse response = new MessageResponse();
//		response.setStatus(status);
//		response.setMessage(message);
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> responses(int status, String message, Map<String, Object> result) {
//		MessageResponse responses = new MessageResponse();
//		responses.setStatus(status);
//		responses.setMessage(message);
//		responses.setResult(result);
//		return ResponseEntity.ok(responses);
//	}
//
//	protected ResponseEntity<Object> response(Object result) {
//		MessageResponse response = new MessageResponse();
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
//	protected ResponseEntity<Object> response(List<Object> result) {
//		MessageResponse response = new MessageResponse();
//		response.setResult(result);
//		return ResponseEntity.ok(response);
//	}
//
////	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
////	public ResponseEntity<Object> failure(int status, String error, String message, String path) {
////		MessageResponse errorDetails = new MessageResponse(status, error, message, path);
////		return ResponseEntity.badRequest().body(errorDetails);
////	}
//
////	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
////	public ResponseEntity<Object> failure(int status, String error, String message) {
////		MessageResponse errorDetails = new MessageResponse(status, error, message);
////		return ResponseEntity.badRequest().body(errorDetails);
////	}
//
//	
//	
//}
