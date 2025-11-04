package com.publicsapient.publicsapient.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class HibernateSearchConfiguration {

    @Autowired
    private EntityManager bentityManager;
    
    
}
