package com.chbb.theaketing.feature.theater.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.theater.entity.Theater;
import com.chbb.theaketing.feature.theater.mapper.TheaterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TheaterQueryService {

    private final TheaterMapper theaterMapper;

    public Theater findById(long id) throws Exception {
        Theater theater = theaterMapper.findById(id);
        if (theater == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return theater;
    }

}
