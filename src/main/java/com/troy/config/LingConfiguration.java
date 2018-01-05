package com.troy.config;

import com.lingling.config.LingConfig;
import com.lingling.http.LingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-25
 */
@Configuration
@ConditionalOnClass(LingService.class)
@EnableConfigurationProperties(LingConfig.class)
public class LingConfiguration {

    @Autowired
    private LingConfig lingConfig;

    @Bean
    @ConditionalOnMissingBean
    public LingService lingService() {
        LingService service = new LingService();
        service.setLingConfig(lingConfig);
        return service;
    }
}
