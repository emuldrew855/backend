package com.ebay.queens.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebay.queens.demo.model.UserActions;
import com.ebay.queens.demo.model.ViewOnEbay;

public interface UserActionRepository extends MongoRepository<ArrayList<HashMap<String, Boolean>>,Integer> {

}
