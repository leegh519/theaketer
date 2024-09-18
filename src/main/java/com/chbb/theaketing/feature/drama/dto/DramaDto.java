package com.chbb.theaketing.feature.drama.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.drama.entity.Drama;
import com.chbb.theaketing.feature.theater.entity.Theater;

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
        protected Long id;

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
        protected Theater theater;

        @Builder
        public DramaListRes(Long id, String title, String thumbnailImage, LocalDate startDate, LocalDate endDate,
                Theater theater) {
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
        protected Long id;

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
        protected Theater theater;

        @Schema(description = "예매 오픈일")
        @NonNull
        protected LocalDateTime openDateTime;

        @Schema(description = "예매 마감일")
        @NonNull
        protected LocalDateTime closeDateTime;

        @Schema(description = "1인 예매 제한수")
        @NonNull
        protected Long limitCount;

        @Schema(description = "상세 이미지")
        @NonNull
        protected String descriptionImage;

        @Schema(description = "1장 가격")
        @NonNull
        protected Long price;

        @Builder
        public DramaDetailRes(Long id, String title, String thumbnailImage, LocalDate startDate, LocalDate endDate,
                Theater theater, LocalDateTime openDateTime, LocalDateTime closeDateTime, Long limitCount,
                String descriptionImage, Long price) {
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
            this.price = price;
        }

        public DramaDetailRes(Drama drama, Theater theater) {
            this.id = drama.getId();
            this.title = drama.getTitle();
            this.thumbnailImage = drama.getThumbnailImage();
            this.startDate = drama.getStartDate();
            this.endDate = drama.getEndDate();
            this.theater = theater;
            this.openDateTime = drama.getOpenDateTime();
            this.closeDateTime = drama.getCloseDateTime();
            this.limitCount = drama.getLimitCount();
            this.descriptionImage = drama.getDescriptionImage();
            this.price = drama.getPrice();
        }
    }

    @Getter
    @Schema(description = "예매 내역 연극 정보")
    @NoArgsConstructor
    public static class DramaReservationRes {

        @Schema(description = "연극id")
        @NonNull
        protected Long id;

        @Schema(description = "연극이름")
        @NonNull
        protected String title;

        @Schema(description = "썸네일 이미지")
        @NonNull
        protected String thumbnailImage;

        @Schema(description = "극장")
        @NonNull
        protected Theater theater;

        @Builder
        public DramaReservationRes(Long id, String title, String thumbnailImage, Theater theater) {
            this.id = id;
            this.title = title;
            this.thumbnailImage = thumbnailImage;
            this.theater = theater;
        }
    }
}
