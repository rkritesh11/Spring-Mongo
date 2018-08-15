package com.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.domains.DataConfig;

public interface DataConfigMongoRepository extends MongoRepository<DataConfig, String>, DataConfigCustomMongoRepository{

	DataConfig findById(String configId);
	
	void deleteById(String configId);
}
