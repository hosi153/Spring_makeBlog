package com.example._spring_blog.dto;

import org.springframework.data.domain.Page;
import lombok.Getter;

import java.util.List;

@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page){
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber(),
                page.getSize(),page.getTotalElements(),page.getTotalPages()
        );
    }
}
