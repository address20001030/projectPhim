package com.example.webflux.service;


import com.example.webflux.exception.InvalidTokenException;
import com.example.webflux.exception.UnkownIdentifierException;
import com.example.webflux.model.response.user.AccountResponse;
import com.example.webflux.model.response.user.UserDetailResponse;

public interface CustomerAccountService {

    AccountResponse forgottenPassword(final String username) throws UnkownIdentifierException;

    UserDetailResponse updatePassword(final String password, final String token) throws InvalidTokenException, UnkownIdentifierException;

    boolean loginDisabled(final String username);

}
