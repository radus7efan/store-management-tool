package com.demo.smt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * App configration.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.demo.smt.config"
})
public class AppConfig {
}
