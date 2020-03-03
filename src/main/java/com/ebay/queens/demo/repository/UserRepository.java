package com.ebay.queens.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.ebay.queens.demo.model.User;


@Repository
public interface UserRepository  extends MongoRepository<User,Integer>{
	
}
