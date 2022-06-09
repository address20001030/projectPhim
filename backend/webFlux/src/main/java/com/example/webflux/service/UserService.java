package com.example.webflux.service;



import com.example.webflux.exception.InvalidTokenException;
import com.example.webflux.exception.UnkownIdentifierException;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.user.UserAuthRequest;
import com.example.webflux.model.request.user.UserSaveRequest;
import com.example.webflux.model.request.user.UserUpdateRequest;
import com.example.webflux.model.response.user.UserDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.server.reactive.ServerHttpRequest;

public interface UserService {
    UserDetailResponse save(UserSaveRequest userSaveRequest);

    void sendRegistrationConfirmationEmail(User user);

    boolean checkIfUserExist(String email);

    boolean verifyUser(String token) throws InvalidTokenException;

    User getUserById(final String email) throws UnkownIdentifierException;

    User auth(UserAuthRequest request);

    UserDetailResponse getInfo(ServerHttpRequest request);

    UserDetailResponse getUser(long id);

    String deleteUser(long id);

    UserDetailResponse updateUser(long id, UserUpdateRequest userUpdateRequest);

    Page<UserDetailResponse> getAll(Pageable pageable);
}
