//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.base.ExceptionController;
//import com.example.demo.basemodel.Response;
//import com.example.demo.model.OrderDetail;
//import com.example.demo.repo.OrderDetailRepo;
//
//@RestController
//public class OrderDetailController extends ExceptionController {
//	
//	@Autowired
//	OrderDetailRepo orderDetailRepo;
//	@PostMapping("/addtest")
//	public Response createOrderDetail( @RequestBody OrderDetail orderDetail) {
//		OrderDetail addOrder = new OrderDetail();
//		addOrder.setAmount(orderDetail.getAmount());
//		addOrder.setPrice(orderDetail.getPrice());
//		addOrder.setQuanity(orderDetail.getQuanity());
//		addOrder.setProduct(orderDetail.getProduct());
//		addOrder.setOrder(orderDetail.getOrder());
//		return response("Add Succcessfully", addOrder);
//		
//		
//		
//		 
//		
//	}
//
//}
