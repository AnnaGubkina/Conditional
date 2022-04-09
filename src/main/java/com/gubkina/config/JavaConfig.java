package com.gubkina.config;

import com.gubkina.profile.DevProfile;
import com.gubkina.profile.ProductionProfile;
import com.gubkina.profile.SystemProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JavaConfig {

    private static final Logger log = LoggerFactory.getLogger(JavaConfig.class);

    @PostConstruct
    public void init(){
        log.warn("app is loaded!");
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
