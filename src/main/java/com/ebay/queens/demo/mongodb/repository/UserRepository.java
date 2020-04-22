package com.ebay.queens.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ebay.queens.demo.mongodb.model.User;


@Repository
public interface UserRepository  extends MongoRepository<User,Integer>{
	
}
