package com.chbb.theaketing.feature.reservation.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.auth.service.SecurityService;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.drama.entity.Drama;
import com.chbb.theaketing.feature.drama.entity.ShowTime;
import com.chbb.theaketing.feature.drama.service.DramaQueryService;
import com.chbb.theaketing.feature.drama.service.ShowTimeQueryService;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationSearchReq;
import com.chbb.theaketing.feature.reservation.entity.Reservation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationCommandService reservationCommandService;

    private final ReservationQueryService reservationQueryService;

    private final DramaQueryService dramaQueryService;

    private final ShowTimeQueryService showTimeQueryService;

    private final SecurityService securityService;

    public void reserve(ReservationDto.ReservationReq req) throws Exception {

        Drama drama = dramaQueryService.findById(req.getDramaId());

        ShowTime time = showTimeQueryService.findById(req.getShowTimeId());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(time.getShowDate(), time.getStartTime());
        if (ChronoUnit.HOURS.between(now, start) < 1) {
            throw new TheaketingException(ErrorCode.TIME_INVALID);
        }

        Reservation reservation = Reservation.builder()
                .dramaId(req.getDramaId())
                .showTimeId(req.getShowTimeId())
                .ticketCount(req.getTicketCount())
                .userId(securityService.getUser().getId())
                .price(req.getTicketCount() * drama.getPrice())
                .build();
        reservationCommandService.insert(reservation);
    }

    public Page<ReservationRes> paginate(PageDto page) throws Exception {
        ReservationSearchReq req = new ReservationSearchReq(securityService.getUser().getId(), page);
        return reservationQueryService.paginate(req);
    }

}
