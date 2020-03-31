package com.ebay.queens.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;


@Repository
public interface CharityRepository  extends MongoRepository<GetCharityResult,Integer>{
	
}
