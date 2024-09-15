package com.chbb.theaketing.feature.drama.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.drama.dto.TheaterDto.TheaterInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DramaDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "연극 검색")
    public static class DramaSearchReq extends PageDto {

        @Schema(description = "검색어(연극이름)", requiredMode = RequiredMode.NOT_REQUIRED)
        @Nullable
        protected String searchValue;
    }

    @Getter
    @Schema(description = "연극 목록")
    @NoArgsConstructor
    public static class DramaListRes {

        @Schema(description = "연극id")
        @NonNull
        protected Integer id;

        @Schema(description = "연극이름")
        @NonNull
        protected String title;

        @Schema(description = "썸네일 이미지")
        @NonNull
        protected String thumbnailImage;

        @Schema(description = "시작일")
        @NonNull
        protected LocalDate startDate;

        @Schema(description = "종료일")
        @NonNull
        protected LocalDate endDate;

        @Schema(description = "극장")
        @NonNull
        protected TheaterDto.TheaterInfo theater;

        @Builder
        public DramaListRes(Integer id, String title, String thumbnailImage, LocalDate startDate, LocalDate endDate,
                TheaterInfo theater) {
            this.id = id;
            this.title = title;
            this.thumbnailImage = thumbnailImage;
            this.startDate = startDate;
            this.endDate = endDate;
            this.theater = theater;
        }
    }

    @Getter
    @Schema(description = "연극 상세")
    @NoArgsConstructor
    public static class DramaDetailRes {

        @Schema(description = "연극id")
        @NonNull
        protected Integer id;

        @Schema(description = "연극이름")
        @NonNull
        protected String title;

        @Schema(description = "썸네일 이미지")
        @NonNull
        protected String thumbnailImage;

        @Schema(description = "시작일")
        @NonNull
        protected LocalDate startDate;

        @Schema(description = "종료일")
        @NonNull
        protected LocalDate endDate;

        @Schema(description = "극장")
        @NonNull
        protected TheaterDto.TheaterInfo theater;

        @Schema(description = "예매 오픈일")
        @NonNull
        protected LocalDateTime openDateTime;

        @Schema(description = "예매 마감일")
        @NonNull
        protected LocalDateTime closeDateTime;

        @Schema(description = "1인 예매 제한수")
        @NonNull
        protected Integer limitCount;

        @Schema(description = "작품 상세 이미지")
        @NonNull
        protected String descriptionImage;

        @Builder
        public DramaDetailRes(Integer id, String title, String thumbnailImage, LocalDate startDate, LocalDate endDate,
                TheaterInfo theater, LocalDateTime openDateTime, LocalDateTime closeDateTime, Integer limitCount,
                String descriptionImage) {
            this.id = id;
            this.title = title;
            this.thumbnailImage = thumbnailImage;
            this.startDate = startDate;
            this.endDate = endDate;
            this.theater = theater;
            this.openDateTime = openDateTime;
            this.closeDateTime = closeDateTime;
            this.limitCount = limitCount;
            this.descriptionImage = descriptionImage;
        }
    }

    @Getter
    @Schema(description = "예매 내역 연극 정보")
    @NoArgsConstructor
    public static class DramaReservationRes {

        @Schema(description = "연극id")
        @NonNull
        protected Integer id;

        @Schema(description = "연극이름")
        @NonNull
        protected String title;

        @Schema(description = "썸네일 이미지")
        @NonNull
        protected String thumbnailImage;

        @Schema(description = "극장")
        @NonNull
        protected TheaterDto.TheaterInfo theater;

        @Builder
        public DramaReservationRes(Integer id, String title, String thumbnailImage, TheaterInfo theater) {
            this.id = id;
            this.title = title;
            this.thumbnailImage = thumbnailImage;
            this.theater = theater;
        }
    }
}
