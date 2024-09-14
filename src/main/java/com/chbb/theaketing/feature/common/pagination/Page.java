package com.chbb.theaketing.feature.common.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "페이징 데이터")
public class Page<T> {

    @Schema(description = "컨텐츠 리스트")
    @NonNull
    protected List<T> content = new ArrayList<>();

    @Schema(description = "총 컨텐츠 개수")
    protected long totalCount;

    @Schema(description = "읽어온 컨텐츠 크기")
    protected int itemSize;

    @Schema(description = "페이지 번호")
    protected int pageNum;

    @Schema(description = "페이지 크기")
    protected int pageSize;

    // TODO 임시 생성자
    public Page() {
        this.itemSize = 1;
        this.pageSize = 1;
        this.pageNum = 1;
    }

    public Page(List<T> content, PageDto pageDto, long totalCount) {
        // TODO content null 체크
        // TODO pageDto null 체크
        this.content = content;
        this.itemSize = pageDto.getItemSize();
        this.pageSize = pageDto.getPageSize();
        this.pageNum = pageDto.getPageNum();
        this.totalCount = totalCount;
    }

    @Schema(description = "총 페이지 수")
    public long getTotalPage() {
        return (totalCount - 1) / itemSize + 1;
    }

    @Schema(description = "하단 시작 페이지")
    public int getStartPage() {
        return ((pageNum - 1) / pageSize) * pageSize + 1;
    }

    @Schema(description = "하단 마지막 페이지")
    public long getEndPage() {
        long totalPage = getTotalPage();
        long endPage = getStartPage() + pageSize - 1;
        if (endPage > totalPage)
            endPage = totalPage;
        return endPage;
    }

}
