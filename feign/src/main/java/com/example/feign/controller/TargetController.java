package com.example.feign.controller;

import com.example.feign.common.dto.BaseResponseInfo;
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
}
