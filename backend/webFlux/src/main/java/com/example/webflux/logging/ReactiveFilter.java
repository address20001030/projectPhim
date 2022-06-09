package com.example.webflux.logging;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Builder
public final class ReactiveFilter implements WebFilter {

    private final String[] ignorePaths;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final long startTime = System.currentTimeMillis();
        List<String> header = exchange.getRequest().getHeaders().get("Content-Length");
        if (header == null || header.get(0).equals("0")) {
            log.info("Request: method={}, uri = {}, audit = {}",
                    exchange.getRequest().getMethod(),
                    exchange.getRequest().getURI().getPath(),
                    exchange.getRequest().getHeaders(), true);
        }

        ServerWebExchangeDecorator exchangeDecorator = new ServerWebExchangeDecorator(exchange) {
            @Override
            public @NonNull
            ServerHttpRequest getRequest() {
                return new RequestInterceptor(super.getRequest());
            }

            @Override
            public @NonNull
            ServerHttpResponse getResponse() {
                return new ResponseInterceptor(this, super.getResponse(), System.currentTimeMillis());
            }
        };
        return chain.filter(exchangeDecorator)
                .doOnSuccess(aVoid -> {
                })
                .doOnError(throwable -> {
                });
    }
}
