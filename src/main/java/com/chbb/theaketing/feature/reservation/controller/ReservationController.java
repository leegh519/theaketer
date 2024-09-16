package com.chbb.theaketing.feature.reservation.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;
import com.chbb.theaketing.feature.reservation.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "예매", description = "예매 API")
@Slf4j
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/u/v1/reserve")
    @Operation(summary = "예매", description = "예매")
    public void reserve(@RequestBody @Valid ReservationDto.ReservationReq req) throws Exception {
        reservationService.reserve(req);
        return;
    }

    @DeleteMapping("/u/v1/reserve/{id}")
    @Operation(summary = "예매 취소", description = "예매 취소")
    public void cancel(@PathVariable @Min(value = 1, message = "예매 정보가 올바르지 않습니다") Long id) {
        // TODO 연극 예매 취소 로직
        return;
    }

    @GetMapping("/u/v1/reserve")
    @Operation(summary = "예매 내역", description = "예매 내역")
    public Page<ReservationRes> paginate(PageDto page) throws Exception {
        return reservationService.paginate(page);
    }
}
