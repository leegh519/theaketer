package com.chbb.theaketing.feature.drama.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DramaDateTimeDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 날짜")
    public static class DramaDate {

        @Schema(description = "연극 날짜")
        @NonNull
        protected LocalDate date;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 회차")
    public static class DramaTime {

        @Schema(description = "연극 회차")
        @NonNull
        protected LocalTime time;

        @Schema(description = "남은 좌석")
        @NonNull
        protected Long remainSeats;

    }
}
