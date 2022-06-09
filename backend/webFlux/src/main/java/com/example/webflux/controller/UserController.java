package com.example.webflux.controller;


import com.example.webflux.exception.InvalidTokenException;
import com.example.webflux.exception.UnkownIdentifierException;
import com.example.webflux.model.entity.Role;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.user.*;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.security.jwt.TokenProducer;
import com.example.webflux.security.jwt.model.JwtPayload;
import com.example.webflux.service.CustomerAccountService;
import com.example.webflux.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;


@RestController

public class UserController extends BaseController {

    private final UserService userService;
    private final CustomerAccountService customerAccountService;
    private final TokenProducer tokenProducer;


    public UserController(UserService userService, CustomerAccountService customerAccountService, TokenProducer tokenProducer) {
        this.userService = userService;
        this.customerAccountService = customerAccountService;
        this.tokenProducer = tokenProducer;
    }

    @PostMapping("/user/register")
    public Mono<BaseResponse> register(@RequestBody UserSaveRequest userSaveRequest){
        return success(userService.save(userSaveRequest));
    }

    @PostMapping("/api/register/verify")
    public Mono<BaseResponse> verifyCustomer(@RequestParam(required = false) String token) throws InvalidTokenException {
        return success(userService.verifyUser(token));
    }

    @PostMapping("/password/request")
    public Mono<BaseResponse> resetPassword(@RequestBody AccountRequest accountRequest) throws UnkownIdentifierException {
        return success(customerAccountService.forgottenPassword(accountRequest.getEmail()));
    }

    @PostMapping("/password/change")
    public Mono<BaseResponse> changesPassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) throws InvalidTokenException, UnkownIdentifierException {
        return success(customerAccountService.updatePassword(updatePasswordRequest.getPassword(), updatePasswordRequest.getToken()));
    }

    @PostMapping("/user/auth")
    public Mono<BaseResponse> auth(@RequestBody UserAuthRequest request){
        return success(getJwt(request));
    }

    @GetMapping("/user/info")
    @PreAuthorize("hasAnyAuthority('ADMIN') OR hasAnyAuthority('USER')")
    public Mono<BaseResponse> getInfo(ServerHttpRequest request){
        return success(userService.getInfo(request));
    }

    @GetMapping("/getUser/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> getUser(@PathVariable long id){
        return success(userService.getUser(id));
    }


    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> deleteUser(@PathVariable long id){
        return success( userService.deleteUser(id));
    }

    @PutMapping("/user/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest userUpdateRequest){
        return success(userService.updateUser(id,userUpdateRequest));
    }


    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public  Mono<BaseResponse> getAllUser(@RequestParam int offset, @RequestParam int limit){
        PageRequest pageRequest = PageRequest.of(offset,limit);
        return success(userService.getAll(pageRequest));
    }


    private String getJwt(UserAuthRequest userAuthRequest){
        User user = userService.auth(userAuthRequest);
        JwtPayload payload = createJwtPayload(user);

        return tokenProducer.token(payload);

    }

    private JwtPayload createJwtPayload(User user){
        JwtPayload payload = new JwtPayload();
        payload.setId(user.getId());
        payload.setUsername(user.getUsername());
        String role = user.getRoles().stream().map(Role::getName).collect(Collectors.joining());
        payload.setRole(role);
        return payload;
    }


}
