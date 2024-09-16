package com.chbb.theaketing.feature.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.payment.dto.PaymentDto;
import com.chbb.theaketing.feature.payment.service.PaymentService;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "결제", description = "결제 API")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/u/v1/payment")
    @Operation(summary = "결제요청", description = "결제요청")
    public ReservationRes payment(@RequestBody @Valid PaymentDto.PaymentReq req) throws Exception {

        return paymentService.payment(req);
    }

}
