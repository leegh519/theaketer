package com.chbb.theaketing.feature.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.reservation.dto.ReservationDto;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "결제", description = "결제 API")
@Slf4j
public class PaymentController {

    @PostMapping("/u/v1/payment")
    @Operation(summary = "결제요청", description = "결제요청")
    public ReservationRes payment(@RequestBody @Valid ReservationDto.ReservationReq req) {
        // TODO 결제 요청 로직
        // 요청 시 결제중으로 상태 변경
        // 결제 연동 완료 후 결제완료로 상태 변경
        // 결제 실패 시 예외 던지고 예매내역도 삭제

        return new ReservationRes();
    }

}
