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

    // Order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다."),
    USER_ORDER_MISMATCH(HttpStatus.FORBIDDEN, "주문자 정보가 결제자와 일치하지 않습니다."),

    // Payment
    UNSUPPORTED_PAYMENT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 방식입니다."),
    UNSUPPORTED_PG_NAME(HttpStatus.BAD_REQUEST, "지원하지 않는 PG사입니다."),
    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제가 실패하였습니다." );


    private final HttpStatus httpStatus;
    private final String message;

}
