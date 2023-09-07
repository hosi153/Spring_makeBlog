package com.example._spring_blog.article.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ArticlePostDto {


    private String articleTitle;
    private String userName;
    @Email
    @NotBlank
    private String email;
    private String password;
    private String content;


}
