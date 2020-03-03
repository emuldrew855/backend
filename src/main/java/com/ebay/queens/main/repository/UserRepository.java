package com.ebay.queens.main.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.ebay.queens.main.model.User;


@Repository
public interface UserRepository  extends MongoRepository<User,String>{
	
}
