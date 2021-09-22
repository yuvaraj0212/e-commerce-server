//package com.example.demo.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.base.ExceptionController;
//import com.example.demo.basemodel.Response;
//import com.example.demo.model.Product;
//import com.example.demo.repo.ProductRepo;
//
//@RestController
//public class ProductController extends ExceptionController {
//
//	@Autowired
//	ProductRepo productRepo;
//	@Autowired
//	BaseController baseController;
//
//	@PostMapping(value = "/create/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public Response creatProduct(Product product) throws Exception {
//
//		Product product1 = new Product();
//
//		if ((product.getMfile()) != null
//				&& (product.getMfile()).getOriginalFilename().split(Pattern.quote(".")).length > 1) {
//			System.out.println("testfile");
//			File tfile = baseController.multipartToFile(product.getMfile());
//			product1.setFilename(product.getMfile().getOriginalFilename());
//			product1.setData(baseController.readContentIntoByteArray(tfile));
//			product1.setCreateDate(new Date());
//			product1.setName(product.getName());
//			product1.setDiscount(product.getDiscount());
//			product1.setPrice(product.getPrice());
//			product1.setDetails(product.getDetails());
//			product1.setCode(product.getCode());
//			productRepo.save(product1);
//			return response("Add Succcessfully", product1);
//
//		}
//		return null;
//
//	}
//
//	@PostMapping(value = "/edit/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public Response editProduct(Product product) throws IOException {
//		Product editProduct = productRepo.findById(product.getId()).get();
//		if ((product.getMfile()) != null
//				&& (product.getMfile()).getOriginalFilename().split(Pattern.quote(".")).length > 1) {
//			System.out.println("file");
//			File tfile = baseController.multipartToFile(product.getMfile());
//
//			editProduct.setFilename(product.getMfile().getOriginalFilename());
//			editProduct.setData(baseController.readContentIntoByteArray(tfile));
//		}
//		editProduct.setName(product.getName());
//		editProduct.setDiscount(product.getDiscount());
//		editProduct.setPrice(product.getPrice());
//		editProduct.setDetails(product.getDetails());
//		editProduct.setCode(product.getCode());
//		productRepo.save(editProduct);
//		return response("Add Succcessfully", editProduct);
//
//	}
//
//	@RequestMapping(value = "/datalist/product", method = RequestMethod.GET)
//
//	public List<Product> productDetails(
//			HttpServletResponse response1/* , @PageableDefault(size = 7) Pageable pageable */) {
//		List<Product> userdetails = new ArrayList<>();
//		userdetails = productRepo.findAll();
//
//		Collections.reverse(userdetails);
//		userdetails.forEach(action -> {
//			try {
//				baseController.writeByte(action.getData(), action.getFilename());
//			} catch (Exception e) {
//				e.getMessage();
//			}
//		});
//		return userdetails;
//	}
//
//	@RequestMapping(value = "/delete/product", method = { RequestMethod.DELETE })
//	@ResponseBody
//	public String deleteProduct(@RequestParam(name = "id") String id) {
//		productRepo.delete(productRepo.findById(Long.parseLong(id)).get());
//		return "delete product";
//	}
//
//}
