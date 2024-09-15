package com.chbb.theaketing.feature.drama.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.drama.dto.DramaDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto.DramaListRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaQueryService dramaQueryService;

    public Page<DramaListRes> paginate(DramaDto.DramaSearchReq req) throws Exception {
        return dramaQueryService.paginate(req);
    }

}
