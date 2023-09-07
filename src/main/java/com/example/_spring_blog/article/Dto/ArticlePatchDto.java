package com.example._spring_blog.article.Dto;


import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class ArticlePatchDto {

    private long articleId;


    private String articleTitle;
    private String userName;
    private String password;
    @Email
    @NotBlank
    private String email;

    private String content;

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
