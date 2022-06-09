package com.example.webflux.email.context;


import com.example.webflux.model.entity.User;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext{
    private String token;

    @Override
    public <T> void init(T context) {
        User customer = (User) context;
        put("username",customer.getUsername());
        setTemplateLocation("email/email-verification");
        setSubject("Hoàn tất quá trình đăng ký!");
        setFrom("Huynguyenmau93@gmail.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token){
        this.token = token;
        put("token",token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/verify").queryParam("token",token).toUriString();
        put("verification",url);
    }
}
