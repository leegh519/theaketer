package com.chbb.theaketing.feature.drama.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.drama.dto.DramaDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto.DramaListRes;
import com.chbb.theaketing.feature.drama.entity.Drama;
import com.chbb.theaketing.feature.drama.mapper.DramaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DramaQueryService {

    private final DramaMapper dramaMapper;

    public Page<DramaListRes> paginate(DramaDto.DramaSearchReq req) throws Exception {
        List<DramaListRes> content = dramaMapper.paginate(req);
        long totalCount = dramaMapper.count(req);
        return new Page<DramaListRes>(content, req, totalCount);
    }

    public Drama findById(long id) throws Exception {
        Drama drama = dramaMapper.findById(id);
        if (drama == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return drama;
    }

}