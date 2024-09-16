package com.chbb.theaketing.feature.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "결제 요청")
    public static class PaymentReq {

        @Schema(description = "예매 id", requiredMode = RequiredMode.NOT_REQUIRED)
        @Min(value = 1, message = "예매 정보가 올바르지 않습니다")
        protected Long reservationId;

    }

}
