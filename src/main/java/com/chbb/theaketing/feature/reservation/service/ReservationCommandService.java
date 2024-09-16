package com.chbb.theaketing.feature.reservation.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.reservation.entity.Reservation;
import com.chbb.theaketing.feature.reservation.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationCommandService {

    private final ReservationMapper reservationMapper;

    public Long insert(Reservation reservation) throws Exception {
        if (reservation == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        try {
            reservationMapper.insert(reservation);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new TheaketingException(ErrorCode.RESERVATION_DUPLICATE);
        }
        return reservation.getId();
    }

    public void delete(Long id) throws Exception {
        reservationMapper.delete(id);
    }

}
