package com.chbb.theaketing.feature.drama.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.drama.dto.DramaDto;
import java.util.List;

@Mapper
public interface DramaMapper {

    public List<DramaDto.DramaListRes> paginate(DramaDto.DramaSearchReq req) throws Exception;

    public long count(DramaDto.DramaSearchReq req) throws Exception;

}
