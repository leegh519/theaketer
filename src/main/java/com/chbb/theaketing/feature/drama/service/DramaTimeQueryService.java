package com.chbb.theaketing.feature.drama.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;
import com.chbb.theaketing.feature.drama.mapper.DramaTimeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DramaTimeQueryService {

    private final DramaTimeMapper dramaTimeMapper;

    public List<DramaDate> dramaDate(Long id) throws Exception {
        List<DramaDate> list = dramaTimeMapper.findDatesByDramaId(id);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}
