package com.ebay.queens.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.model.ViewOnEbay;

public interface UserActionRepository extends MongoRepository<ViewOnEbay,Integer> {

}
