package com.example.webflux.mapper.user;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.user.UserSaveRequest;


@org.mapstruct.Mapper(componentModel = "spring")
public interface UserSaveMapper extends Mapper<User, UserSaveRequest> {
}
