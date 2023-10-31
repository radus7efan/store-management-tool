package com.demo.smt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration class for MongoDB.
 */
@Configuration
@EnableMongoRepositories({"com.demo.smt.persistance.repository"})
@EntityScan("com.demo.smt.domain.model")
public class MongoConfig {

}
