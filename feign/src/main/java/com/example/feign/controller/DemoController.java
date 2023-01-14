package com.example.feign.controller;

import com.example.feign.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/get")
    public String getController() {
        return demoService.get();
    }

    @PostMapping("/post")
    public String postController() {
        return demoService.post();
    }

}
