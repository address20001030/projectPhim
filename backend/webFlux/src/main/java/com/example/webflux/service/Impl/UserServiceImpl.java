package com.example.webflux.service.Impl;


import com.example.webflux.email.context.AccountVerificationEmailContext;
import com.example.webflux.email.service.EmailService;
import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import com.example.webflux.exception.InvalidTokenException;
import com.example.webflux.exception.UnkownIdentifierException;
import com.example.webflux.mapper.user.UserDetailResponseMapper;
import com.example.webflux.mapper.user.UserSaveMapper;
import com.example.webflux.model.entity.Role;
import com.example.webflux.model.entity.SecureToken;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.user.UserAuthRequest;
import com.example.webflux.model.request.user.UserSaveRequest;
import com.example.webflux.model.request.user.UserUpdateRequest;
import com.example.webflux.model.response.user.UserDetailResponse;
import com.example.webflux.repository.RoleRepository;
import com.example.webflux.repository.SecureTokenRepository;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.security.jwt.TokenConsumer;
import com.example.webflux.security.jwt.model.JwtPayload;
import com.example.webflux.service.SecureTokenService;
import com.example.webflux.service.UserService;
import com.example.webflux.utils.JwtUtil;
import com.example.webflux.utils.PasswordUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserSaveMapper userSaveMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailResponseMapper userDetailResponseMapper;
    private final SecureTokenRepository secureTokenRepository;
    private final SecureTokenService secureTokenService;
    private final EmailService emailService;
    private final TokenConsumer tokenConsumer;

    @Autowired
    public UserServiceImpl(UserSaveMapper userSaveMapper, UserRepository userRepository, RoleRepository roleRepository, UserDetailResponseMapper userDetailResponseMapper, SecureTokenRepository secureTokenRepository, SecureTokenService secureTokenService, EmailService emailService, TokenConsumer tokenConsumer) {
        this.userSaveMapper = userSaveMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailResponseMapper = userDetailResponseMapper;
        this.secureTokenRepository = secureTokenRepository;
        this.secureTokenService = secureTokenService;
        this.emailService = emailService;
        this.tokenConsumer = tokenConsumer;
    }



    @Value("${site.base.url.https}")
    private String baseURL;



    //Tạo mới hoặc đăng ký người dùng
    @Override
    public UserDetailResponse save(UserSaveRequest userSaveRequest) {
        User user = userSaveMapper.to(userSaveRequest);
        user.setPassword(PasswordUtil.getMd5(userSaveRequest.getPassword()));
        Set<Role> roles = roleRepository.findAllByIdIn(Arrays.asList(1l));
        user.setRoles(roles);
        userRepository.save(user);
        sendRegistrationConfirmationEmail(user);
        return userDetailResponseMapper.to(user);

    }

    @Override
    public void sendRegistrationConfirmationEmail(User user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl("http://localhost:3000",secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return true;
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken)|| !StringUtils.equals(token,secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        User user = userRepository.getOne(secureToken.getUser().getId());
        if (Objects.isNull(user)){
            return false;
        }

        user.setAccountVerified(true);
        userRepository.save(user);
        secureTokenService.removeToken(secureToken);
        return false;
    }

    @Override
    public User getUserById(String email) throws UnkownIdentifierException {
        User user = userRepository.findByEmail(email);
        if (user == null || BooleanUtils.isFalse(user.isAccountVerified())){
            throw new UnkownIdentifierException("unable to find account or account is not active");
        }
        return user;
    }


    // Xác thực user bằng username và password
    @Override
    public User auth(UserAuthRequest request) {
        String password = PasswordUtil.getMd5(request.getPassword());
        Optional<User> result = userRepository.findByUsernameAndPasswordAndAccountVerified(request.getUsername(), password, true);
        result.orElseThrow(() -> new BusinessException(BusinessCode.USER_LOGIN_FAIL));
        return result.get();
    }

    @Override
    public UserDetailResponse getInfo(ServerHttpRequest request) {
        try{

            String token = JwtUtil.getToken(request);
            JwtPayload jwtPayload = tokenConsumer.consume(token);
            Long id = jwtPayload.getId();
            Optional<User> result = userRepository.findById(id);
            result.orElseThrow(()->new BusinessException(BusinessCode.USER_NOT_FOUND));

            return userDetailResponseMapper.to(result.get());
        }catch (InvalidJwtException e){
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        }
    }

    @Override
    public UserDetailResponse getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(()->new BusinessException(BusinessCode.USER_NOT_FOUND));
        return userDetailResponseMapper.to(user.get());
    }

    //Xóa người dùng
    @Override
    public String deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(()->new BusinessException(BusinessCode.USER_NOT_FOUND));
        SecureToken secureToken = secureTokenRepository.findByUser(user.get());
        if (secureToken != null){
            if (!secureToken.getToken().isEmpty()){
                secureTokenService.removeToken(secureToken);
                userRepository.delete(user.get());
                return "DELETED";
            }
        }
        return "FAILED";
    }

    //Cập nhật thông tin người dùng
    @Override
    public UserDetailResponse updateUser(long id, UserUpdateRequest userUpdateRequest) {
        Optional<User> result = userRepository.findById(id);
        result.orElseThrow(()-> new BusinessException(BusinessCode.USER_NOT_FOUND));
        User udUser = result.get();
        Set<Role> roles = roleRepository.findAllByIdIn(Arrays.asList(1l));
        udUser.setRoles(roles);
        return userDetailResponseMapper.to(userRepository.save(udUser));
    }

    @Override
    public Page<UserDetailResponse> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable.previousOrFirst());
        return users.map(userDetailResponseMapper::to);
    }


}
