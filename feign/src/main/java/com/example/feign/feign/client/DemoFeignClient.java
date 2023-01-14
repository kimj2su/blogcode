package com.example.feign.feign.client;

import com.example.feign.common.dto.BaseRequestInfo;
import com.example.feign.common.dto.BaseResponseInfo;
import com.example.feign.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "demo-client",
        url = "${feign.url.prefix}",
        configuration = DemoFeignConfig.class
)
public interface DemoFeignClient {

    @GetMapping("/get") // -> http://localhost:8080/target_server/get  get 으로 요청이 감
    ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String customHeader,
                                             @RequestParam("name") String name,
                                             @RequestParam("age") Long age);

    @PostMapping("/post") // -> http://localhost:8080/target_server/post  post 으로 요청이 감
    ResponseEntity<BaseResponseInfo> callPost(@RequestHeader("CustomHeaderName") String customHeader,
                                              @RequestBody BaseRequestInfo baseRequestInfo);
}
