package com.chbb.theaketing.feature.theater.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater {
    @Schema(description = "id")
    @NonNull
    protected Long id;

    @Schema(description = "극장이름")
    @NonNull
    protected String name;

    @Schema(description = "극장 주소")
    @NonNull
    protected String address;

    @Schema(description = "상세주소")
    @Nullable
    protected String addressDetail;

    @Schema(description = "좌석수")
    @NonNull
    protected Long seatCount;

    @Builder
    public Theater(Long id, String name, String address, String addressDetail, Long seatCount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.addressDetail = addressDetail;
        this.seatCount = seatCount;
    }
}
