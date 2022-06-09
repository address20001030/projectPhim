package com.example.webflux.mapper.user;


import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.response.user.AccountResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface AccountMapper extends Mapper<AccountResponse, User> {
}
