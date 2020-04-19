package com.ebay.queens.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.mongodb.model.SearchType;

public interface SearchTypeRepository extends MongoRepository<SearchType,Integer> {

}
