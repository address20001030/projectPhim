package com.example.webflux.security;




import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.security.core.context.SecurityContext;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
@Slf4j
public class ServerSecurityContext implements ServerSecurityContextRepository {

    public static final String AUTH_HEADER = "X-AUTH-TOKEN";
    private final AuthenticationManager authenticator;
    private final TokenManager tokenManager;


    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        return Mono
                .justOrEmpty(serverWebExchange.getRequest().getHeaders().getFirst(AUTH_HEADER))
                .flatMap(token ->{
                        try {
                            log.info("token = {}",token);
                            if (!StringUtils.isEmpty(token)){
                                UserAuthentication userAuth = tokenManager.getAuthentication(token);
                                return this.authenticator.authenticate(userAuth).map(SecurityContextImpl::new);
                            }else{
                                return Mono.error(()->new BusinessException(BusinessCode.TOKEN_BLANK));
                            }

                        } catch (InvalidJwtException e) {
                            BusinessException businessException;
                            if (e.getMessage().contains("no longer valid")) {
                                businessException = new BusinessException(BusinessCode.TOKEN_INVALID);
                            } else {
                                businessException = new BusinessException(BusinessCode.TOKEN_EXPIRED);
                            }

                            return Mono.error(businessException);
                        }

                });

    }

}
