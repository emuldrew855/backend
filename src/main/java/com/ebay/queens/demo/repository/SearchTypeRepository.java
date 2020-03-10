package com.ebay.queens.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.model.SearchType;

public interface SearchTypeRepository extends MongoRepository<SearchType,Integer> {

}
