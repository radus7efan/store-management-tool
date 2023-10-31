package com.demo.smt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the service layer.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.demo.smt.service",
        "com.demo.smt.transformer"
})
public class ServiceConfig {
}
