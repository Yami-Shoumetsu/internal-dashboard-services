package com.ibm.usaa.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ibm.usaa.service.ExpertiseService;
import com.ibm.usaa.service.IntervieweeService;

/**
 * Bean configuration for Services.
 * 
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Configuration
@EntityScan("com.ibm.usaa.repository.entity")
@EnableJpaRepositories(basePackages = "com.ibm.usaa.repository")
@EnableJpaAuditing
public class ServiceConfiguration {

    @Bean
    public IntervieweeService intervieweeService() {
        return new IntervieweeService();
    }

    @Bean
    public ExpertiseService expertiseService() {
        return new ExpertiseService();
    }

}
