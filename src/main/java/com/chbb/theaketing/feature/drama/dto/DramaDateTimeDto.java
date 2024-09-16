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
    @Schema(description = "연극 회차 조회")
    public static class DramaTimeSearch {

        @Schema(description = "연극 id")
        @NonNull
        protected Long dramaId;

        @Schema(description = "연극 날짜")
        @NonNull
        protected LocalDate showDate;

        public DramaTimeSearch(Long dramaId, LocalDate showDate) {
            this.dramaId = dramaId;
            this.showDate = showDate;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 날짜")
    public static class DramaDate {

        @Schema(description = "연극 날짜")
        @NonNull
        protected LocalDate showDate;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 회차")
    public static class DramaTime {

        @Schema(description = "연극 회차 id")
        @NonNull
        protected Long id;

        @Schema(description = "연극 회차")
        @NonNull
        protected LocalTime time;

        @Schema(description = "남은 좌석")
        @NonNull
        protected Long remainSeats;

    }
}
