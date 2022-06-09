package com.example.webflux.exception;

import org.springframework.http.HttpStatus;

public final class BusinessCode {
    public static final ErrorResponse SUCCESS = new ErrorResponse("ONLINE-SUCCESS","SUCCESS", HttpStatus.OK);

    public static final ErrorResponse INTERNAL_SERVER =
            new ErrorResponse("ONLINE-INTERNAL-SERVER", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse TOKEN_BLANK =
            new ErrorResponse("ONLINE-TOKEN-BLANK", "Token is not blank", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse TOKEN_INVALID =
            new ErrorResponse("ONLINE-AUTH-01", "Token invalid", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse TOKEN_EXPIRED =
            new ErrorResponse("ONLINE-TOKEN-EXPIRED", "Token is expired", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse USER_LOGIN_FAIL =
            new ErrorResponse("ONLINE-AUTH-FAIL", "Username or password wrong or account is not active", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse USER_ACTIVE_FAIL =
            new ErrorResponse("ONLINE-ACTIVE-FAIL", "User is not active", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse USER_NOT_FOUND =
            new ErrorResponse("ONLINE-USER-NOT-FOUND", "user not found", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse ERROR_RESPONSE =
            new ErrorResponse("ONLINE-ERROR", "Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    public static final ErrorResponse MOVIE_NOT_FOUND =
            new ErrorResponse("ONLINE-MOVIE-NOT-FOUND", "movie not found", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse CATEGORY_NOT_FOUND =
            new ErrorResponse("ONLINE-CATEGORY-NOT-FOUND", "category not found", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse MOVIE_DETAIL_NOT_FOUND =
            new ErrorResponse("MOVIE-DETAIL-NOT-FOUND", "movie detail not found", HttpStatus.BAD_REQUEST);
    public BusinessCode() {
    }
}
