package com.example.feign.controller;

import com.example.feign.common.dto.BaseRequestInfo;
import com.example.feign.common.dto.BaseResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/target_server")
public class TargetController {

    @GetMapping("/get")
    public BaseResponseInfo demoGet(@RequestHeader("CustomHeaderName") String header,
                                    @RequestParam("name") String name,
                                    @RequestParam("age") Long age) {
        return BaseResponseInfo.builder()
                .header(header)
                .name(name + "from targer server")
                .age(age)
                .build();
    }

    @PostMapping("/post")
    public BaseResponseInfo demoPost(@RequestHeader("CustomHeaderName") String header,
                                                  @RequestBody BaseRequestInfo baseRequestInfo) {
        return BaseResponseInfo.builder()
                .header(header)
                .name(baseRequestInfo.getName() + "from targer server")
                .age(baseRequestInfo.getAge())
                .build();
    }

    @GetMapping("/error")
    public ResponseEntity<BaseResponseInfo> demoErrorDecoder() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
