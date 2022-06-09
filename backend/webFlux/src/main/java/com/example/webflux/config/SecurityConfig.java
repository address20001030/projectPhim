package com.example.webflux.config;

import com.example.webflux.security.AuthenticationManager;
import com.example.webflux.security.ServerSecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
@Slf4j
@Configuration
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    private final ServerSecurityContext serverSecurityContext;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    log.error(e.getCause().toString());
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                }))
                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(serverSecurityContext)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/user/register", "/user/auth","/api/**")
                .permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.GET,"/public/**")
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }
    
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-AUTH-TOKEN", "Cache-Control", "Origin"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(31536000L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
