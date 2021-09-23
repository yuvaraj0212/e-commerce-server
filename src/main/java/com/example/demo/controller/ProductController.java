package com.example.demo.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.base.ExceptionController;
import com.example.demo.model.ProductModel;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController extends ExceptionController {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	ProductService productService;
	

	@PostMapping(value = "/create-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> creatProduct(@Valid ProductModel productModel) throws Exception {
		return productService.createProduct(productModel);
	}

	@PostMapping(value = "/update-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> editProduct(@Valid ProductModel productModel,MultipartFile  file) throws IOException {
		return productService.updateProduct(productModel,file);
	}

	@GetMapping(value = "/product-list")
	public ResponseEntity<Object> productDetails(){
		return productService.getProductList();
	}

	@DeleteMapping(value = "/delete-product")
	public ResponseEntity<Object> deleteProduct(@RequestParam(name = "productId") Long productId) {
		return productService.deleteProduct(productId);
	}

}
