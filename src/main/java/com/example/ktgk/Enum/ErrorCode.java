package com.example.ktgk.Enum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    PRODUCT_NOT_EXISTED( 1001,  "user not existed",HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED( 1002,  "user existed", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    HttpStatus statusCode;
}
