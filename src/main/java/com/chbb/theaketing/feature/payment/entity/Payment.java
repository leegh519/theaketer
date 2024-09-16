package com.chbb.theaketing.feature.payment.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    protected Long id;

    protected Long userId;

    protected Long reservationId;

    protected LocalDateTime paymentDate;

    protected String status;

    @Builder
    public Payment(Long id, Long userId, Long reservationId, LocalDateTime paymentDate, String status) {
        this.id = id;
        this.userId = userId;
        this.reservationId = reservationId;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public void paymentFail() {
        this.status = "실패";
    }

    public void paymentSuccess() {
        this.status = "완료";
    }

}
