package com.chbb.theaketing.feature.payment.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.auth.service.SecurityService;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.payment.dto.PaymentDto;
import com.chbb.theaketing.feature.payment.entity.Payment;
import com.chbb.theaketing.feature.reservation.entity.Reservation;
import com.chbb.theaketing.feature.reservation.service.ReservationQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentCommandService paymentCommandService;

    private final ReservationQueryService reservationQueryService;

    private final SecurityService securityService;

    public void payment(PaymentDto.PaymentReq req) throws Exception {

        Reservation reservation = reservationQueryService.findByIdWithLock(req.getReservationId());
        if (!reservation.getUserId().equals(securityService.getUser().getId())) {
            throw new TheaketingException(ErrorCode.RESERVATION_NOT_MINE);
        }

        // 요청 시 결제중으로 상태 변경
        Payment payment = Payment.builder()
                .userId(securityService.getUser().getId())
                .reservationId(req.getReservationId())
                .status("진행")
                .build();

        try {
            paymentCommandService.insert(payment);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new TheaketingException(ErrorCode.PAYMENT_DUPLICATE);
        }
        try {
            // 결제 연동 완료 후 결제완료로 상태 변경
            paymentProcess(payment);
            payment.paymentSuccess();
            paymentUpdate(payment);
        } catch (Exception e) {
            // 결제 실패 시 예외 던지고 예매내역도 삭제
            payment.paymentFail();
            paymentUpdate(payment);
            e.printStackTrace();
            throw new TheaketingException(ErrorCode.PAYMENT_FAIL);
        }
    }

    private void paymentProcess(Payment payment) throws Exception {
        paymentCommandService.update(payment);
    }

    private void paymentUpdate(Payment payment) throws Exception {
        paymentCommandService.update(payment);
    }
}
