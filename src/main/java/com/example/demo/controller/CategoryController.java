package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ExceptionController;
import com.example.demo.model.CategoryModel;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends ExceptionController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping(value="/create-category")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryModel categoryModel){
		return categoryService.createCategory(categoryModel);
	}
	
	@PostMapping(value="/update-category")
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryModel categoryModel){
		return categoryService.updateCategory(categoryModel);
	}
	
	@GetMapping(value="/category-details")
	public ResponseEntity<Object> getCategory(@RequestParam("categoryId") Long categoryId){
	return categoryService.getCategory(categoryId);
	}

	@GetMapping(value="/category-list")
	public ResponseEntity<Object> categoryList(){
	return categoryService.categoryList();
	}
	
	@DeleteMapping(value="delete-category")
	public ResponseEntity<Object> deleteCategory(@RequestParam("categoryId") Long categoryId){
		return categoryService.deleteCategory(categoryId);
	}
}
