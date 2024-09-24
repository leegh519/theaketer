package com.chbb.theaketing.feature.reservation.service;

import com.chbb.theaketing.feature.drama.entity.ShowTime;
import com.chbb.theaketing.feature.drama.service.ShowTimeCommandService;
import com.chbb.theaketing.feature.drama.service.ShowTimeQueryService;
import com.chbb.theaketing.feature.payment.service.PaymentQueryService;
import com.chbb.theaketing.feature.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationCancelService {

    private final PaymentQueryService paymentQueryService;

    private final ReservationQueryService reservationQueryService;

    private final ReservationCommandService reservationCommandService;

    private final ShowTimeQueryService showTimeQueryService;

    private final ShowTimeCommandService showTimeCommandService;

    @Transactional()
    public void paymentCheck(Long id) throws Exception {
        boolean exist = paymentQueryService.existByReservationId(id);
        if (exist) {
            return;
        }
        Reservation reservation = reservationQueryService.findById(id);
        // 예매 내역 삭제
        reservationCommandService.delete(id);

        ShowTime time = showTimeQueryService.findByIdWithLock(reservation.getShowTimeId());
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
}
