package com.chbb.theaketing.feature.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.common.pagination.PageDto;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationRes;
import com.chbb.theaketing.feature.reservation.dto.ReservationDto.ReservationSearchReq;
import com.chbb.theaketing.feature.reservation.entity.Reservation;

@Mapper
public interface ReservationMapper {

    public void insert(Reservation reservation) throws Exception;

    public Reservation findById(Long id) throws Exception;

    public List<ReservationRes> paginate(ReservationSearchReq page) throws Exception;

    public long count(PageDto page) throws Exception;

}
