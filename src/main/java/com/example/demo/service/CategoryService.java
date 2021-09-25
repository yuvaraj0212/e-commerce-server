package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ExceptionController;
import com.example.demo.model.CategoryModel;
import com.example.demo.repo.CategoryRepo;


@Service
public class CategoryService extends ExceptionController{

	@Autowired
	CategoryRepo categoryRepo;
	
	public ResponseEntity<Object> createCategory(CategoryModel categoryModel){
		boolean model = categoryRepo.existsByName(categoryModel.getName());
		if(model) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"category type already exists");
		}
		categoryModel.setCreatedDate(new Date());
		categoryModel.setModifiedDate(new Date());
		categoryRepo.save(categoryModel);
		return response(HttpStatus.OK.value(),"category created successfully",categoryModel);
	}
	
	public ResponseEntity<Object> updateCategory(CategoryModel categoryModel){
		CategoryModel model = categoryRepo.findById(categoryModel.getId()).get();
		if(model == null) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"category details not available");
		}
		model.setName(categoryModel.getName());
		model.setDesc(categoryModel.getDesc());
		model.setModifiedDate(new Date());
		categoryRepo.save(model);
		return response(HttpStatus.OK.value(),"category updated successfully",categoryModel);
	}
	
	public ResponseEntity<Object> getCategory(Long categoryId){
		CategoryModel categoryModel = categoryRepo.findById(categoryId).get();
		if(categoryModel == null) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"category details not available");
		}
		return response(HttpStatus.OK.value(),"category details",categoryModel);
	}
	
	public ResponseEntity<Object> deleteCategory(Long categoryId){
		CategoryModel categoryModel = categoryRepo.findById(categoryId).get();
		if(categoryModel == null) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"category details not available");
		}
		categoryRepo.delete(categoryModel);
		return response(HttpStatus.OK.value(),"category deleted successfully");
	}
	
	public ResponseEntity<Object> categoryList(){
		List<CategoryModel> categoryModel = categoryRepo.findAll();
		if(categoryModel.isEmpty()) {
			return failure(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"category details not available");
		}
		Collections.reverse(categoryModel);
		return response(HttpStatus.OK.value(),"category deleted successfully");
	}
}
