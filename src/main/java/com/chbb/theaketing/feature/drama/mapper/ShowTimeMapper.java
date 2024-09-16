package com.chbb.theaketing.feature.drama.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTime;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTimeSearch;
import com.chbb.theaketing.feature.drama.entity.ShowTime;

@Mapper
public interface ShowTimeMapper {

    public ShowTime findById(Long id) throws Exception;

    public List<DramaDate> findDatesByDramaId(long id) throws Exception;

    public List<DramaTime> findTimesByDramaIdAndDate(DramaTimeSearch req) throws Exception;

}
