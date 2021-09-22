package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;
@Repository
public interface UserRepo extends JpaRepository<UserModel,Long>{

//	UserModel findByUserName(String username);

	//boolean existsByEmail(String email);
	UserModel findByEmail(String email);

	boolean existsByEmail(String email);

       

}
