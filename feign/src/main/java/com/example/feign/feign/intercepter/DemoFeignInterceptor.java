package com.example.feign.feign.intercepter;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RequiredArgsConstructor(staticName = "of")
public class DemoFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        //get 요청일 경우
        if (Objects.equals(template.method(), Request.HttpMethod.GET.name())) {
            template.header("CustomHeaderName", "aa");
            System.out.println("[GET] [DemoFeignInterceptor] queries : " + template.headers());
            System.out.println("[GET] [DemoFeignInterceptor] queries : " + template.queries());
            return;
        }

        //post 요청일 경우
        String encodedRequestBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        System.out.println("[POST] [DemoFeignInterceptor] requestBody : " + encodedRequestBody);

        //추가적으로 본인이 필요한 로직을 추가

        //encodedRequestBody 스트링이기 때문에  objectmapper를 통해 json 객체로풀어 값을 세팅해주어야한다.
        String convertRequestBody = encodedRequestBody;

        template.body(convertRequestBody);
    }
}
