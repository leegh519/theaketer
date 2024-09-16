package com.chbb.theaketing.feature.drama.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTime;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaTimeSearch;
import com.chbb.theaketing.feature.drama.mapper.DramaTimeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DramaTimeQueryService {

    private final DramaTimeMapper dramaTimeMapper;

    public List<DramaDate> dramaDate(Long id) throws Exception {
        if (id == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        List<DramaDate> list = dramaTimeMapper.findDatesByDramaId(id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public List<DramaTime> dramaDate(DramaTimeSearch req) throws Exception {
        List<DramaTime> list = dramaTimeMapper.findTimesByDramaIdAndDate(req);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
