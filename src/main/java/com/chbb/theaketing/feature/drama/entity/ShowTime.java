package com.chbb.theaketing.feature.drama.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowTime {
    @Schema(description = "회차 id")
    protected Long id;

    @Schema(description = "연극 id")
    protected Long dramaId;

    @Schema(description = "날짜")
    protected LocalDate showDate;

    @Schema(description = "시작시간")
    protected LocalTime startTime;

    @Schema(description = "종료시간")
    protected LocalTime endTime;

    @Schema(description = "남은좌석")
    protected Long remainSeats;

    @Builder
    public ShowTime(Long id, Long dramaId, LocalDate showDate, LocalTime startTime, LocalTime endTime,
            Long remainSeats) {
        this.id = id;
        this.dramaId = dramaId;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remainSeats = remainSeats;
    }

}
