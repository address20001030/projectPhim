package com.example.webflux.mapper.user;


import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.response.user.UserDetailResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserDetailResponseMapper extends Mapper<UserDetailResponse, User> {
}
