package com.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.mongodb.repository")
public class SimpleMongoConfig {

	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "DATACONFIGDB");
	}

}
