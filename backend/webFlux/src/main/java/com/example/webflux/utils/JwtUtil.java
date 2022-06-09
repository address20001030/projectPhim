package com.example.webflux.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class JwtUtil {
    public static final String AUTH_HEADER = "X-AUTH-TOKEN";

    public static String getToken(ServerHttpRequest request){
        HttpHeaders headers = request.getHeaders();
        if (headers.containsKey(AUTH_HEADER)){
            return headers.get(AUTH_HEADER).get(0);
        }
        return null;
    }

}
