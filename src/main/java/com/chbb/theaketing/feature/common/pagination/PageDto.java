package com.chbb.theaketing.feature.common.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto {

    @Schema(description = "검색 페이지 번호", example = "1")
    @Min(1)
    protected int pageNum = 1;

    @Schema(hidden = true)
    protected int pageSize = 5;

    @Schema(hidden = true)
    protected int totalCount = 0;

    @Schema(description = "데이터 갯수", example = "15")
    protected int itemSize = 15;

    @Schema(hidden = true)
    public int getOffset() {
        return (pageNum - 1) * itemSize;
    }

}
