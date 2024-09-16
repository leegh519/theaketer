package com.chbb.theaketing.feature.reservation.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chbb.theaketing.feature.auth.service.SecurityService;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.drama.entity.Drama;
import com.chbb.theaketing.feature.drama.entity.ShowTime;
import com.chbb.theaketing.feature.drama.service.DramaQueryService;
import com.chbb.theaketing.feature.drama.service.ShowTimeQueryService;
import com.chbb.theaketing.feature.payment.service.PaymentCommandService;
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

    private final PaymentCommandService paymentCommandService;

    private final DramaQueryService dramaQueryService;

    private final ShowTimeQueryService showTimeQueryService;

    private final SecurityService securityService;

    public Long reserve(ReservationDto.ReservationReq req) throws Exception {

        Drama drama = dramaQueryService.findById(req.getDramaId());

        ShowTime time = showTimeQueryService.findById(req.getShowTimeId());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(time.getShowDate(), time.getStartTime());
        if (ChronoUnit.HOURS.between(now, start) < 1) {
            throw new TheaketingException(ErrorCode.RESERVATION_TIME_INVALID);
        }

        Reservation reservation = Reservation.builder()
                .dramaId(req.getDramaId())
                .showTimeId(req.getShowTimeId())
                .ticketCount(req.getTicketCount())
                .userId(securityService.getUser().getId())
                .price(req.getTicketCount() * drama.getPrice())
                .build();
        return reservationCommandService.insert(reservation);
    }

    public Page<ReservationRes> paginate(PageDto page) throws Exception {
        ReservationSearchReq req = new ReservationSearchReq(securityService.getUser().getId(), page);
        return reservationQueryService.paginate(req);
    }

    @Transactional
    public void cancel(Long id) throws Exception {
        // 권한 확인
        Reservation reservation = reservationQueryService.findById(id);
        if (!reservation.getUserId().equals(securityService.getUser().getId())) {
            throw new TheaketingException(ErrorCode.HAS_NO_AUTHORITY);
        }
        // 날짜 확인
        ShowTime time = showTimeQueryService.findById(reservation.getShowTimeId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(time.getShowDate(), time.getStartTime());
        if (ChronoUnit.HOURS.between(now, start) < 1) {
            throw new TheaketingException(ErrorCode.CANCEL_TIME_INVALID);
        }
        // 결제 내역 삭제
        paymentCommandService.delete(id);

        // 예매 내역 삭제
        reservationCommandService.delete(id);
    }

}
