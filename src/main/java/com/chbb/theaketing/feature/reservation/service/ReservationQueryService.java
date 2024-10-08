package com.chbb.theaketing.feature.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationSearchReq;
import com.chbb.theaketing.feature.reservation.entity.Reservation;
import com.chbb.theaketing.feature.reservation.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional(readOnly = false)
    public Reservation findByIdWithLock(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        Reservation reservation = reservationMapper.findByIdWithLock(id);
        if (reservation == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return reservation;
    }

    public Page<ReservationRes> paginate(ReservationSearchReq req) throws Exception {
        List<ReservationRes> content = reservationMapper.paginate(req);
        long totalCount = reservationMapper.count(req);
        return new Page<ReservationRes>(content, req, totalCount);
    }

    public boolean existByShowTimeIdAndUserId(Long showTimeId, Long userId) throws Exception {
        if (showTimeId == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        if (userId == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        Reservation reservation = Reservation.builder()
                .showTimeId(showTimeId)
                .userId(userId)
                .build();
        return reservationMapper.existByShowTimeIdAndUserId(reservation);
    }

    public Reservation findByShowTimeIdAndUserId(Long showTimeId, Long userId) throws Exception {
        if (showTimeId == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        if (userId == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        Reservation reservation = Reservation.builder()
                .showTimeId(showTimeId)
                .userId(userId)
                .build();
        Reservation result = reservationMapper.findByShowTimeIdAndUserId(reservation);
        if (result == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return result;
    }

}
