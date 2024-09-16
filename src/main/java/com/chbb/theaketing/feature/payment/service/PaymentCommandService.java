package com.chbb.theaketing.feature.payment.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.payment.entity.Payment;
import com.chbb.theaketing.feature.payment.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentCommandService {

    private final PaymentMapper paymentMapper;

    public void insert(Payment payment) throws Exception {
        if (payment == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        paymentMapper.insert(payment);
    }

    public void update(Payment payment) throws Exception {
        if (payment == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        paymentMapper.update(payment);
    }

}
