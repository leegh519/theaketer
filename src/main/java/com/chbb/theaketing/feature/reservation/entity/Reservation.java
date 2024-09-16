package com.chbb.theaketing.feature.reservation.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Schema(description = "예약 id")
    protected Long id;

    @Schema(description = "유저 id")
    protected Long userId;

    @Schema(description = "연극 id")
    protected Long dramaId;

    @Schema(description = "회차 id")
    protected Long showTimeId;

    @Schema(description = "예매 장 수")
    protected Long ticketCount;

    @Schema(description = "가격")
    protected Long price;

    @Builder
    public Reservation(Long id, Long userId, Long dramaId, Long showTimeId, Long ticketCount, Long price) {
        this.id = id;
        this.userId = userId;
        this.dramaId = dramaId;
        this.showTimeId = showTimeId;
        this.ticketCount = ticketCount;
        this.price = price;
    }
}
