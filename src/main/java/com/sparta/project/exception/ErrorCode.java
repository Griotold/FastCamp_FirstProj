package com.sparta.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // UNAUTHORIZED & FORBIDDEN
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 정보가 일치하지 않습니다."),

    // NOT FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다."),

    // Payment
    UNSUPPORTED_PAYMENT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 방식입니다."),
    UNSUPPORTED_PG_NAME(HttpStatus.BAD_REQUEST, "지원하지 않는 PG사입니다."),
    PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "결제가 실패하였습니다." ),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 가게 정보가 존재하지 않습니다."),

    // Location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 위치 정보가 존재하지 않습니다."),

    // StoreCategory
    STORE_CATEGROY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 음식점 카테고리 정보가 존재하지 않습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
