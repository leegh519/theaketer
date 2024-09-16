package com.chbb.theaketing.feature.drama.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto.DramaDate;
import com.chbb.theaketing.feature.drama.dto.DramaDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto.DramaDetailRes;
import com.chbb.theaketing.feature.drama.dto.DramaDto.DramaListRes;
import com.chbb.theaketing.feature.drama.entity.Drama;
import com.chbb.theaketing.feature.theater.entity.Theater;
import com.chbb.theaketing.feature.theater.service.TheaterQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaQueryService dramaQueryService;

    private final DramaTimeQueryService dramaTimeQueryService;

    private final TheaterQueryService theaterQueryService;

    public Page<DramaListRes> paginate(DramaDto.DramaSearchReq req) throws Exception {
        return dramaQueryService.paginate(req);
    }

    public DramaDetailRes fetch(long id) throws Exception {
        Drama drama = dramaQueryService.findById(id);
        Theater theater = theaterQueryService.findById(drama.getTheaterId());
        return new DramaDetailRes(drama, theater);
    }

    public List<DramaDate> dramaDate(Long id) throws Exception {
        return dramaTimeQueryService.dramaDate(id);
    }

}
