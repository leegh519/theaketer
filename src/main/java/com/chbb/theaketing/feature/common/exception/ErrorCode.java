package com.chbb.theaketing.feature.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    DATA_NOT_FOUND(404, "C001", "데이터가 존재하지 않습니다."),
    NOT_NULL_PARAMETER(400, "C002", "파라미터가 null입니다."),
    UNKNOWN(400, "C999", "문제가 발생했습니다."),

    // Auth
    EMAIL_SEND_ERROR(400, "A001", "메일 전송에 실패했습니다."),
    CODE_EXPIRED(400, "A002", "인증코드가 만료되었습니다."),
    WRONG_CODE(400, "A003", "인증코드가 일치하지 않습니다."),
    NOT_EMAIL_AUTHENTICATE(400, "A004", "이메일 인증이 필요합니다."),
    AUTHENTICATION_FAIL(400, "A005", "아이디나 비밀번호가 잘못되었습니다."),
    EMAIL_DUPLICATE(400, "A006", "사용중인 이메일입니다."),

    // Reservation
    RESERVATION_DUPLICATE(400, "R001", "예매 내역이 존재합니다."),
    RESERVATION_TIME_INVALID(400, "R002", "시작 1시간 전까지 예매 가능합니다."),
    CANCEL_TIME_INVALID(400, "R003", "시작 1시간 전까지 취소 가능합니다."),
    RESERVATION_NOT_MINE(400, "R004", "예매 정보가 올바르지 않습니다."),
    HAS_NO_AUTHORITY(400, "R005", "권한이 없습니다."),
    NO_REMAIN_SEATS(400, "R006", "남은 좌석이 없습니다."),

    // payment
    PAYMENT_FAIL(400, "P001", "결제에 실패했습니다."),
    PAYMENT_DUPLICATE(400, "P002", "이미 결제된 예매입니다."),

    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}
