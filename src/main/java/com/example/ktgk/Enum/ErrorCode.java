package com.example.ktgk.Enum;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    PRODUCT_NOT_EXISTED( 1001,  "user not existed"),
    PRODUCT_EXISTED( 1002,  "user existed"),
    ;
    int code;
    String message;
}
