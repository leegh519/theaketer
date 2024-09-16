package com.chbb.theaketing.feature.reservation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.reservation.entity.Reservation;

@Mapper
public interface ReservationMapper {

    public void insert(Reservation reservation) throws Exception;

}
