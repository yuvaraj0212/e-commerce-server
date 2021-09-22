package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query("SELECT coalesce(max(u.id),0 )FROM Order u")
	String findById();  

}
