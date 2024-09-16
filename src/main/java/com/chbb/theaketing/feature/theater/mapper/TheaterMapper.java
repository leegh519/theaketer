package com.chbb.theaketing.feature.theater.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.theater.entity.Theater;

@Mapper
public interface TheaterMapper {

    public Theater findById(long id) throws Exception;

}
