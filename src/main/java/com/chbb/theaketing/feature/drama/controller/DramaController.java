package com.chbb.theaketing.feature.drama.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.common.pagination.Page;
import com.chbb.theaketing.feature.drama.dto.DramaDateTimeDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto;
import com.chbb.theaketing.feature.drama.dto.DramaDto.DramaListRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "연극", description = "연극 API")
@Slf4j
public class DramaController {

    @GetMapping("/u/v1/drama")
    @Operation(summary = "연극 목록 조회", description = "연극 목록 조회")
    public Page<DramaListRes> paginate(@ParameterObject @Valid DramaDto.DramaSearchReq req) {
        log.info("" + req.getItemSize());
        // TODO 연극 목록 조회 로직
        return new Page<DramaListRes>();
    }

    // 연극 상세 조회
    @GetMapping("/u/v1/drama/{id}")
    @Operation(summary = "연극 상세 조회", description = "연극 상세 조회")
    public DramaDto.DramaDetailRes fetch(@PathVariable @Min(value = 1, message = "연극 id값이 올바르지 않습니다") Integer id) {
        // TODO 연극 상세 조회 로직
        return new DramaDto.DramaDetailRes();
    }

    // 연극 날짜 조회
    @GetMapping("/u/v1/drama/{id}/date")
    @Operation(summary = "연극 날짜 조회", description = "연극 날짜 조회")
    public List<DramaDateTimeDto.DramaDate> dramaDate(
            @PathVariable @Min(value = 1, message = "연극 id값이 올바르지 않습니다") Integer id) {
        // TODO 연극 날짜 조회 로직
        return new ArrayList<>();
    }

    // 연극 회차 조회
    @GetMapping("/u/v1/drama/{id}/{date}/time")
    @Operation(summary = "연극 회차 조회", description = "연극 회차 조회")
    public List<DramaDateTimeDto.DramaTime> dramaTime(
            @PathVariable @Min(value = 1, message = "연극 id값이 올바르지 않습니다") Integer id,
            @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
        // TODO 연극 날짜 조회 로직
        return new ArrayList<>();
    }

}
