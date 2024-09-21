package com.chbb.theaketing.feature.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.payment.entity.Payment;

@Mapper
public interface PaymentMapper {

    public void insert(Payment payment) throws Exception;

    public void update(Payment payment) throws Exception;

    public void delete(Long reservationId) throws Exception;

    public Payment findById(Long id) throws Exception;

    public boolean existByReservationId(Long id) throws Exception;

}
