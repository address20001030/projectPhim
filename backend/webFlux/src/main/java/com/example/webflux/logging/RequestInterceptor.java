package com.example.webflux.logging;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;


@Log4j2
public final class RequestInterceptor extends ServerHttpRequestDecorator {

    public RequestInterceptor(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return super.getBody().doOnNext(dataBuffer -> {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
                Channels.newChannel(baos).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                String body = baos.toString(String.valueOf(StandardCharsets.UTF_8));
                log.info("Request: method={}, uri={}, payload={}, audit={}", getDelegate().getMethod(),
                        getDelegate().getPath(), body, true);
            } catch (IOException e) {
                log.error("RequestInterceptor exception: {0}", e.getCause().toString());
            }
        });
    }
}
