package com.chbb.theaketing.feature.reservation.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

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
import com.chbb.theaketing.feature.drama.service.ShowTimeCommandService;
import com.chbb.theaketing.feature.drama.service.ShowTimeQueryService;
import com.chbb.theaketing.feature.payment.service.PaymentCommandService;
import com.chbb.theaketing.feature.payment.service.PaymentQueryService;
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

    private final PaymentQueryService paymentQueryService;

    private final DramaQueryService dramaQueryService;

    private final ShowTimeQueryService showTimeQueryService;

    private final ShowTimeCommandService showTimeCommandService;

    private final SecurityService securityService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    @Transactional
    public Long reserve(ReservationDto.ReservationReq req) throws Exception {
        Long userId = securityService.getUser().getId();

        if (reservationQueryService.existByShowTimeIdAndUserId(req.getShowTimeId(), userId)) {
            return reservationQueryService.findByShowTimeIdAndUserId(req.getShowTimeId(), userId).getId();
        }
        ShowTime time = showTimeQueryService.findByIdWithLock(req.getShowTimeId());

        Drama drama = dramaQueryService.findById(req.getDramaId());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(time.getShowDate(), time.getStartTime());
        if (ChronoUnit.HOURS.between(now, start) < 1) {
            throw new TheaketingException(ErrorCode.RESERVATION_TIME_INVALID);
        }

        // 예매 내역 등록
        Reservation reservation = Reservation.builder()
                .dramaId(req.getDramaId())
                .showTimeId(req.getShowTimeId())
                .ticketCount(req.getTicketCount())
                .userId(userId)
                .price(req.getTicketCount() * drama.getPrice())
                .build();
        Long id = reservationCommandService.insert(reservation);

        // 남은 좌석 변경
        ShowTime update = ShowTime.builder()
                .id(time.getId())
                .dramaId(time.getDramaId())
                .showDate(time.getShowDate())
                .startTime(time.getStartTime())
                .endTime(time.getEndTime())
                .remainSeats(time.getRemainSeats() - req.getTicketCount())
                .build();

        showTimeCommandService.update(update);
        // 예매하고 일정시간(3분)안에 결제가 안이루어지면 예매정보를 삭제시키기
        scheduler.schedule(() -> {
            try {
                paymentCheck(id);
            } catch (Exception e) {
            }
        }, 3, TimeUnit.MINUTES);
        return id;
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

        // 남은 좌석 변경
        ShowTime update = ShowTime.builder()
                .id(time.getId())
                .dramaId(time.getDramaId())
                .showDate(time.getShowDate())
                .startTime(time.getStartTime())
                .endTime(time.getEndTime())
                .remainSeats(time.getRemainSeats() + reservation.getTicketCount())
                .build();

        showTimeCommandService.update(update);
    }

    @Transactional
    private void paymentCheck(Long id) throws Exception {
        boolean exist = paymentQueryService.existByReservationId(id);
        if (exist) {
            return;
        }
        Reservation reservation = reservationQueryService.findByIdWithLock(id);
        // 예매 내역 삭제
        reservationCommandService.delete(id);

        ShowTime time = showTimeQueryService.findById(reservation.getShowTimeId());
        // 남은 좌석 변경
        ShowTime update = ShowTime.builder()
                .id(time.getId())
                .dramaId(time.getDramaId())
                .showDate(time.getShowDate())
                .startTime(time.getStartTime())
                .endTime(time.getEndTime())
                .remainSeats(time.getRemainSeats() + reservation.getTicketCount())
                .build();

        showTimeCommandService.update(update);
    }

    @Transactional
    public Long testtt(ReservationDto.ReservationReq req, Long userId) throws Exception {

        ShowTime time = showTimeQueryService.findByIdWithLock(req.getShowTimeId());

        Drama drama = dramaQueryService.findById(req.getDramaId());

        // 예매 내역 등록
        Reservation reservation = Reservation.builder()
                .dramaId(req.getDramaId())
                .showTimeId(req.getShowTimeId())
                .ticketCount(req.getTicketCount())
                .userId(userId)
                .price(req.getTicketCount() * drama.getPrice())
                .build();
        Long id = reservationCommandService.insert(reservation);

        // 남은 좌석 변경
        ShowTime update = ShowTime.builder()
                .id(time.getId())
                .dramaId(time.getDramaId())
                .showDate(time.getShowDate())
                .startTime(time.getStartTime())
                .endTime(time.getEndTime())
                .remainSeats(time.getRemainSeats() - req.getTicketCount())
                .build();

        showTimeCommandService.update(update);
        // 예매하고 일정시간(3분)안에 결제가 안이루어지면 예매정보를 삭제시키기
        scheduler.schedule(() -> {
            try {
                paymentCheck(id);
            } catch (Exception e) {
            }
        }, 3, TimeUnit.MINUTES);
        return id;
    }

}
