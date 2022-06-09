package com.example.webflux;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebFluxApplication.class, args);
    }


}
