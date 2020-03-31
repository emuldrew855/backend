package com.ebay.queens.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.model.SearchType;

public interface SearchTypeRepository extends MongoRepository<SearchType,Integer> {

}
