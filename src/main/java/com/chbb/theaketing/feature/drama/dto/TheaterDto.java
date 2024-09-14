package com.chbb.theaketing.feature.drama.dto;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TheaterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "극장정보")
    public static class TheaterInfo {
        @Schema(description = "id")
        @NonNull
        protected Integer id;

        @Schema(description = "극장이름")
        @NonNull
        protected String name;

        @Schema(description = "극장 주소")
        @NonNull
        protected String address;

        @Schema(description = "상세주소")
        @NonNull
        protected String addressDetail;

        @Schema(description = "좌석수")
        @NonNull
        protected Integer seatCount;

        @Builder
        public TheaterInfo(Integer id, String name, String address, String addressDetail, Integer seatCount) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.addressDetail = addressDetail;
            this.seatCount = seatCount;
        }
    }

}
