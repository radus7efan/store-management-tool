package com.demo.smt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for controller.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.demo.smt.controller"
})
public class ControllerConfig {
}
