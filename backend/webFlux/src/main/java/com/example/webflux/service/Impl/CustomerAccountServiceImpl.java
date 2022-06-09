package com.example.webflux.service.Impl;


import com.example.webflux.email.context.ForgotPasswordEmailContext;
import com.example.webflux.email.service.EmailService;
import com.example.webflux.exception.InvalidTokenException;
import com.example.webflux.exception.UnkownIdentifierException;
import com.example.webflux.mapper.user.AccountMapper;
import com.example.webflux.mapper.user.UserDetailResponseMapper;
import com.example.webflux.model.entity.SecureToken;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.response.user.AccountResponse;
import com.example.webflux.model.response.user.UserDetailResponse;
import com.example.webflux.repository.SecureTokenRepository;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.CustomerAccountService;
import com.example.webflux.service.SecureTokenService;
import com.example.webflux.service.UserService;
import com.example.webflux.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Objects;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserService userService;

    @Resource
    private SecureTokenService secureTokenService;

    @Resource
    private SecureTokenRepository secureTokenRepository;

    @Resource
    private UserDetailResponseMapper userDetailResponseMapper;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Resource
    private EmailService emailService;


    @Resource
    private UserRepository userRepository;

    @Override
    public AccountResponse forgottenPassword(String username) throws UnkownIdentifierException {
        User user = userService.getUserById(username);
        sendResetPasswordEmail(user);
        return accountMapper.to(user);
    }

    @Override
    public UserDetailResponse updatePassword(String password, String token) throws InvalidTokenException, UnkownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if (Objects.isNull(secureToken) || !StringUtils.equals(token,secureToken.getToken())||secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        User user = userRepository.getOne(secureToken.getUser().getId());
        if (Objects.isNull(user)){
            throw new UnkownIdentifierException("Unable to find user for the token");
        }

        secureTokenService.removeToken(secureToken);
        user.setPassword(PasswordUtil.getMd5(password));
        userRepository.save(user);

        return userDetailResponseMapper.to(user);
    }

    protected void sendResetPasswordEmail(User user){
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL,secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginDisabled(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.isLoginDisabled() : false;
    }
}
