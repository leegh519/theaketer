package com.chbb.theaketing.feature.reservation.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.auth.service.SecurityService;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto;
import com.chbb.theaketing.feature.reservation.entity.Reservation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationCommandService reservationCommandService;

    private final SecurityService securityService;

    public void reserve(ReservationDto.ReservationReq req) throws Exception {

        Reservation reservation = Reservation.builder()
                .dramaId(req.getDramaId())
                .showTimeId(req.getShowTimeId())
                .ticketCount(req.getTicketCount())
                .userId(securityService.getUser().getId())
                .build();
        reservationCommandService.insert(reservation);
    }

}
