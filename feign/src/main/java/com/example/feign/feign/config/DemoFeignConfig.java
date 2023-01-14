package com.example.feign.feign.config;


import com.example.feign.feign.decoder.DemoFeignErrorDecoder;
import com.example.feign.feign.intercepter.DemoFeignInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DemoFeignConfig {
    @Bean
    public DemoFeignInterceptor feignInterceptor() {
        return DemoFeignInterceptor.of();
    }

    @Bean
    public DemoFeignErrorDecoder demoFeignErrorDecoder() {
        return new DemoFeignErrorDecoder();
    }
}
