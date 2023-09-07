package com.example._spring_blog.article.Dto;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class ArticleResponseDto {

    private long articleId;

    private String articleTitle;
    private String userName;
    private String password;
    @Email
    @NotBlank
    private String email;

    private String content;
}
