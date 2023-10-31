package com.demo.smt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for persistance layer.
 */
@Configuration
@EnableJpaRepositories({"com.demo.smt.persistance.repository"})
@EntityScan("com.demo.smt.domain.model")
public class RepositoryConfig {

}
