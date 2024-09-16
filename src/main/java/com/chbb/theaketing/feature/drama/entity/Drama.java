package com.chbb.theaketing.feature.drama.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drama {
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
    protected Long theaterId;

    @Schema(description = "예매 오픈일")
    @NonNull
    protected LocalDateTime openDateTime;

    @Schema(description = "예매 마감일")
    @NonNull
    protected LocalDateTime closeDateTime;

    @Schema(description = "1인 예매 제한수")
    @NonNull
    protected Long limitCount;

    @Builder
    public Drama(Long id, String title, String thumbnailImage, LocalDate startDate, LocalDate endDate,
            Long theaterId, LocalDateTime openDateTime, LocalDateTime closeDateTime, Long limitCount) {
        this.id = id;
        this.title = title;
        this.thumbnailImage = thumbnailImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.theaterId = theaterId;
        this.openDateTime = openDateTime;
        this.closeDateTime = closeDateTime;
        this.limitCount = limitCount;
    }

}
