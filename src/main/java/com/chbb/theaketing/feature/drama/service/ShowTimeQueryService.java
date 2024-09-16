package com.chbb.theaketing.feature.drama.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTime;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTimeSearch;
import com.chbb.theaketing.feature.drama.entity.ShowTime;
import com.chbb.theaketing.feature.drama.mapper.ShowTimeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowTimeQueryService {

    private final ShowTimeMapper showTimeMapper;

    public ShowTime findById(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        ShowTime showTime = showTimeMapper.findById(id);
        if (showTime == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return showTime;
    }

    public List<DramaDate> dramaDate(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        List<DramaDate> list = showTimeMapper.findDatesByDramaId(id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DramaTime> dramaDate(DramaTimeSearch req) throws Exception {
        List<DramaTime> list = showTimeMapper.findTimesByDramaIdAndDate(req);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
