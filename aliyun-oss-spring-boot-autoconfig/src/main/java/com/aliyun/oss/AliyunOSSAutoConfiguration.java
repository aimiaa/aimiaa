package com.aliyun.oss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOSSAutoConfiguration {

    @Bean
    public AliyunOSSOperator aliyunOSSOperator() {
        return new AliyunOSSOperator();
    }
}
