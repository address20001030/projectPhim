package com.example.webflux.logging;


import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public final class ResponseInterceptor extends ServerHttpResponseDecorator {
    private final long startTime;
    private final ServerWebExchangeDecorator decorator;

    public ResponseInterceptor(ServerWebExchangeDecorator decorator, ServerHttpResponse delegate,
                               long startTime) {
        super(delegate);
        this.decorator = decorator;
        this.startTime = startTime;
    }

    @Override
    public @NonNull
    Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        Flux<DataBuffer> buffer = Flux.from(body);
        return super.writeWith(buffer.doOnNext(dataBuffer -> {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                Channels.newChannel(baos).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                String bodyRes = baos.toString(String.valueOf(StandardCharsets.UTF_8));
                log.info("Response({} ms): status={}, payload={}, audit={}", System.currentTimeMillis() - startTime,
                        Objects.requireNonNull(getStatusCode()).value(), bodyRes, true);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("ResponseInterceptor exception: {}", e.getCause().toString());
            }
        }));
    }

    private String getBody(ServerWebExchangeDecorator decorator) {
        AtomicReference<String> bodyRef = new AtomicReference<>();
        decorator.getRequest().getBody().doOnNext(dataBuffer -> {
            try {
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
                DataBufferUtils.release(dataBuffer);
                bodyRef.set(charBuffer.toString());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        return bodyRef.get();

    }
}
