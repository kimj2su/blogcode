package com.example.springsecurityoauh2resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.springsecurityoauh2resourceserver.jcajce.MacTest.hmac;
import static com.example.springsecurityoauh2resourceserver.jcajce.MessageDigestTest.messageDigest;
import static com.example.springsecurityoauh2resourceserver.jcajce.RSATest.rsa;
import static com.example.springsecurityoauh2resourceserver.jcajce.SignatureTest.signature;

@SpringBootApplication
public class SpringSecurityOauth2ResourceServerApplication {

    public static void main(String[] args) throws Exception {
//        messageDigest("Spring Security");
//        signature("Spring Security");
        hmac("Spring Security");
//        rsa("Spring Security");
//        SpringApplication.run(SpringSecurityOauth2ResourceServerApplication.class, args);
    }

}
