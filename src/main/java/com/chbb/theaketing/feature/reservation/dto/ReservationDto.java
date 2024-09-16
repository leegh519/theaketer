package com.chbb.theaketing.feature.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 예매")
    public static class ReservationReq {

        @Schema(description = "연극 id", requiredMode = RequiredMode.REQUIRED)
        @Min(value = 1, message = "연극 정보가 잘못되었습니다")
        protected Long dramaId;

        @Schema(description = "회차 id", requiredMode = RequiredMode.REQUIRED)
        @Min(value = 1, message = "회차 정보가 잘못되었습니다")
        protected Long showTimeId;

        @Schema(description = "예매 수량", requiredMode = RequiredMode.REQUIRED)
        @Min(value = 1, message = "티켓 수량이 잘못되었습니다")
        protected Long ticketCount;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "예매 목록 검색")
    public static class ReservationSearchReq extends PageDto {

        @Schema(description = "유저id")
        @Nullable
        protected Long userId;

        public ReservationSearchReq(Long userId, PageDto page) {
            this.userId = userId;
            super.itemSize = page.getItemSize();
            super.pageNum = page.getPageNum();
            super.pageSize = page.getPageSize();
            super.totalCount = page.getTotalCount();
        }
    }

    @Getter
    @Schema(description = "예매 내역 목록")
    @NoArgsConstructor
    public static class ReservationRes {

        @Schema(description = "예매id")
        @NonNull
        protected Long id;

        @Schema(description = "예매 수량")
        protected Long ticketCount;

        @Schema(description = "가격")
        protected Long price;

        // 시간정보
        @Schema(description = "회차")
        @NonNull
        protected ReservationDateRes date;

        // 연극정보
        @Schema(description = "연극")
        @NonNull
        protected DramaDto.DramaReservationRes drama;

    }

    @Getter
    @Schema(description = "연극 회차 정보")
    @NoArgsConstructor
    public static class ReservationDateRes {

        @Schema(description = "연극 회차 id")
        @NonNull
        protected Long id;

        @Schema(description = "연극 날짜")
        @NonNull
        protected LocalDate showDate;

        @Schema(description = "연극 시작시간")
        @NonNull
        protected LocalTime startTime;

    }

}
