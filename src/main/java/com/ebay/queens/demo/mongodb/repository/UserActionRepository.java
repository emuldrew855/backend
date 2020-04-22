package com.ebay.queens.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.mongodb.model.ViewOnEbay;

public interface UserActionRepository extends MongoRepository<ViewOnEbay,Integer> {

}
