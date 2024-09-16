package com.chbb.theaketing.feature.reservation.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.reservation.entity.Reservation;
import com.chbb.theaketing.feature.reservation.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {

    private final ReservationMapper reservationMapper;

    public Reservation findById(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        Reservation reservation = reservationMapper.findById(id);
        if (reservation == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return reservation;
    }

}
