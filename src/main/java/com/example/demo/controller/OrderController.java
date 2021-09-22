//package com.example.demo.controller;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.core.Transient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.base.ExceptionController;
//import com.example.demo.basemodel.Response;
//import com.example.demo.exception.SimplesConstant;
//import com.example.demo.model.Order;
//import com.example.demo.model.Product;
//import com.example.demo.repo.OrderRepo;
//
//@RestController
//@Transient
//public class OrderController extends ExceptionController  {
//
//	@Autowired
//	private JavaMailSender mailSender;
//	@Autowired
//	OrderRepo orderRepo;
//
//	@RequestMapping(value = "/add/orders", method = RequestMethod.POST)
//
//	public Response creatOrder(@RequestBody Order order) {
//
//		Order addOrder = new Order();
//		String orderLastPrimaryId = orderRepo.findById();
//		int maxnum = Integer.parseInt(orderLastPrimaryId);
//		maxnum += 1;
//		String intger = Integer.toString(maxnum);
//		System.out.println("maxstr" + intger);
//		   String oderId="OR_NO"+intger;  
//			addOrder.setOrderNo(oderId);
//		addOrder.setCustomerAddress(order.getCustomerAddress());
//		// addOrder.setCustomerCity(order.getCustomerCity());
//		addOrder.setCustomerEmail(order.getCustomerEmail());
//		addOrder.setAmount(order.getAmount());
//		addOrder.setCustomerAddress(order.getCustomerAddress());
//		addOrder.setCustomerPhone(order.getCustomerPhone());
//		addOrder.setOrderDate(new Date());
//		addOrder.setCustomerName(order.getCustomerName());
//		addOrder.setCustomerZip(order.getCustomerZip());
//        addOrder.setCustomerState(order.getCustomerState());
//		orderRepo.save(addOrder);
//		Runnable task1 = new Runnable() {
//			
//			@Override
//			public void run() {
//				SimpleMailMessage email = new SimpleMailMessage();
//				email.setFrom("Productivity Tracker <yenminproductivitytracker@gmail.com>");
//				email.setSubject("your order is confirmed successfully");
//				email.setText("welcome to shaping word");
//				email.setTo(order.getCustomerEmail());
//				mailSender.send(email);
//				System.out.println("test");
//			}
//		};
//
//		Thread thread1 = new Thread(task1);
//		thread1.start();
//		return response("Add Succcessfully", addOrder);
//
//
//	}
//	
//	@RequestMapping(value = "/datalist/order", method = RequestMethod.GET)
//	public List<Order> Orderdetails(HttpServletResponse response1/* , @PageableDefault(size = 7) Pageable pageable */) {
//		List<Order> orderdetails = new ArrayList<>();
//		orderdetails = orderRepo.findAll();
//		Collections.reverse(orderdetails);
//		orderdetails.forEach(action -> {	
//		});
//		//response1.setHeader("Content-Range", "users 0-" + productRepo.count() + "/" + productRepo.count());
//				return orderdetails;
//	
//}
//	@RequestMapping(value = "/delete/order", method = { RequestMethod.DELETE })
//	@ResponseBody
//	public String deleteOrder(@RequestParam(name = "id") String id) {
//		orderRepo.delete(orderRepo.findById(Long.parseLong(id)).get());
//		return "delete order";
//	}
//	@RequestMapping(value = "/edit/orders", method = RequestMethod.POST)
//	public Response editOrder(@RequestBody Order order) {
//        Order addOrder = orderRepo.findById(order.getId()).get();
//		//Order addOrder = new Order();
//		addOrder.setCustomerAddress(order.getCustomerAddress());
//		// addOrder.setCustomerCity(order.getCustomerCity());
//		addOrder.setCustomerEmail(order.getCustomerEmail());
//		addOrder.setAmount(order.getAmount());
//		addOrder.setCustomerAddress(order.getCustomerAddress());
//		addOrder.setCustomerPhone(order.getCustomerPhone());
//		addOrder.setOrderDate(new Date());
//		addOrder.setCustomerName(order.getCustomerName());
//		//addOrder.setOrderNum(order.getOrderNum());
//		addOrder.setCustomerZip(order.getCustomerZip());
//        addOrder.setCustomerState(order.getCustomerState());
//		 orderRepo.save(addOrder);
//			return response("Edit Succcessfully", addOrder);
//
//	}
//}
