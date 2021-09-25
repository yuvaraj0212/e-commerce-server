package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.ExceptionController;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.ProductModel;
import com.example.demo.pojo.FileStorageProperties;
import com.example.demo.pojo.ProductRequest;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService extends ExceptionController {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	FileStorageService fileStorageService;

	public ResponseEntity<Object> createProduct(ProductRequest productRequest) {
		boolean productExists = productRepo.existsByNameAndCode(productRequest.getProductName(),
				productRequest.getProductCode());
		if (productExists) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"product name/code already exists");
		} else if ((productRequest.getMfile()) == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"File must not empty");
		}

		CategoryModel categoryModel = categoryRepo.findByName(productRequest.getCategory());
		if (categoryModel == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"please check the category type");
		}
		ProductModel productModel = new ProductModel(productRequest);
		String fileName = null;
		try {
			fileName = fileStorageService.storeFile(productRequest.getMfile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/downloadFile/")
				.path(fileName).toUriString();
		productModel.setFilename(fileName);
		productModel.setImageURL(fileDownloadUri);
		productModel.setCategory(categoryModel);
		productModel.setCreateDate(new Date());
		productModel.setModifiedDate(new Date());
		productRepo.save(productModel);
		return response(HttpStatus.OK.value(), "Add Succcessfully", productModel);
	}

	public ResponseEntity<Object> updateProduct(ProductModel productModel) {
		if(productModel.getId() == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"product id must not to be null");
		}
		ProductModel editProduct = productRepo.findById(productModel.getId()).get();
		if ((productModel.getMfile()) == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"File must not empty");
		}
		String fileName = null;
		try {
			fileName = fileStorageService.storeFile(productModel.getMfile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/downloadFile/")
				.path(fileName).toUriString();
		editProduct.setFilename(fileDownloadUri);
		editProduct.setName(productModel.getName());
		editProduct.setDiscount(productModel.getDiscount());
		editProduct.setPrice(productModel.getPrice());
		editProduct.setDetails(productModel.getDetails());
		editProduct.setCode(productModel.getCode());
		productRepo.save(editProduct);
		return response(HttpStatus.OK.value(), "Add Succcessfully", editProduct);
	}

	public ResponseEntity<Object> getProductList() {
		List<ProductModel> productList = productRepo.findAll();
		Collections.reverse(productList);
//		productList.forEach(action -> {
//			try {
//				baseController.writeByte(action.getData(), action.getFilename());
//			} catch (Exception e) {
//				e.getMessage();
//			}
//		});
		return response(HttpStatus.OK.value(), "product list", productList);
	}

	public ResponseEntity<Object> deleteProduct(Long productId) {
		ProductModel productDetail = productRepo.findById(productId).get();
		if (productDetail == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Please check the product details");
		}
		productRepo.delete(productDetail);
		return response(HttpStatus.OK.value(), "product deleted successfully");
	}

	public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) throws Exception {

		Resource resource = fileStorageService.loadFileAsResource(fileName);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
//			logger.info("Could not determine file type.");
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
