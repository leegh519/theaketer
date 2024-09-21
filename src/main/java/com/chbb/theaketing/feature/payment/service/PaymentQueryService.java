package com.chbb.theaketing.feature.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.payment.entity.Payment;
import com.chbb.theaketing.feature.payment.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentQueryService {

    private final PaymentMapper paymentMapper;

    public Payment findById(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        Payment payment = paymentMapper.findById(id);
        if (payment == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return payment;
    }

    public boolean existByReservationId(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        return paymentMapper.existByReservationId(id);
    }

}
