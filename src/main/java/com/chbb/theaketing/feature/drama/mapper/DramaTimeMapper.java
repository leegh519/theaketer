package com.chbb.theaketing.feature.drama.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;

@Mapper
public interface DramaTimeMapper {

    public List<DramaDate> findDatesByDramaId(long id) throws Exception;

}
