package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.base.ExceptionController;
import com.example.demo.controller.BaseController;
import com.example.demo.model.ProductModel;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService extends ExceptionController {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	BaseController baseController;

	
	public ResponseEntity<Object> createProduct(ProductModel productModel){
//		ProductModel product = new ProductModel();
		System.out.println(productModel.getMfile());
		if (productModel.getMfile().getOriginalFilename() == null) {
				return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"File must not empty");
		}
			File tfile = null;
			try {
				tfile = baseController.multipartToFile(productModel.getMfile());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productModel.setFilename(productModel.getMfile().getOriginalFilename());
			productModel.setData(baseController.readContentIntoByteArray(tfile));
			productModel.setCreateDate(new Date());
			productRepo.save(productModel);
			return response(HttpStatus.OK.value(),"Add Succcessfully", productModel);
		
	}
	
	
	public ResponseEntity<Object> updateProduct(ProductModel productModel,MultipartFile  file){
		ProductModel editProduct = productRepo.findById(productModel.getId()).get();
		if ((productModel.getMfile()) != null
				&& (productModel.getMfile()).getOriginalFilename().split(Pattern.quote(".")).length > 1) {
			File tfile = null;
			try {
				tfile = baseController.multipartToFile(productModel.getMfile());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			editProduct.setFilename(productModel.getMfile().getOriginalFilename());
			editProduct.setData(baseController.readContentIntoByteArray(tfile));
		}else {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"File must not empty");
		}
		editProduct.setName(productModel.getName());
		editProduct.setDiscount(productModel.getDiscount());
		editProduct.setPrice(productModel.getPrice());
		editProduct.setDetails(productModel.getDetails());
		editProduct.setCode(productModel.getCode());
		productRepo.save(editProduct);
		return response(HttpStatus.OK.value(),"Add Succcessfully", editProduct);
	}
	
	public ResponseEntity<Object> getProductList(){
		List<ProductModel> productList =  productRepo.findAll();
		Collections.reverse(productList);
		productList.forEach(action -> {
			try {
				baseController.writeByte(action.getData(), action.getFilename());
			} catch (Exception e) {
				e.getMessage();
			}
		});
		return response(HttpStatus.OK.value(),"product list", productList);
	}
	
	public ResponseEntity<Object> deleteProduct(Long productId){
		ProductModel productDetail= productRepo.findById(productId).get();
		if(productDetail == null) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"Please check the product details");
		}
		productRepo.delete(productDetail);
		return  response(HttpStatus.OK.value(),"product deleted successfully");
	}
}
