package com.example.feign.service;

import com.example.feign.common.dto.BaseRequestInfo;
import com.example.feign.common.dto.BaseResponseInfo;
import com.example.feign.feign.client.DemoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoFeignClient demoFeignClient;

    public String get() {
        ResponseEntity<BaseResponseInfo> response =
                demoFeignClient.callGet("CustomHeader", "CustomName", 1L);

        System.out.println("Name = " + Objects.requireNonNull(response.getBody()).getName());
        System.out.println("Age : " + response.getBody().getAge());
        System.out.println("Header : " + response.getBody().getHeader());
        return "get";
    }

    public String post() {
        BaseRequestInfo baseRequestInfo = BaseRequestInfo.builder()
                .name("customName")
                .age(2L)
                .build();
        ResponseEntity<BaseResponseInfo> response =
                demoFeignClient.callPost("CustomHeader", baseRequestInfo);

        System.out.println("Name = " + Objects.requireNonNull(response.getBody()).getName());
        System.out.println("Age : " + response.getBody().getAge());
        System.out.println("Header : " + response.getBody().getHeader());
        return "post";
    }

    public String errorDecoder() {
        demoFeignClient.callErrorDecoder();

        return "error";
    }
}
