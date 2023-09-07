package com.example._spring_blog.article.Mapper;

import com.example._spring_blog.article.Dto.ArticlePatchDto;
import com.example._spring_blog.article.Dto.ArticlePostDto;
import com.example._spring_blog.article.Dto.ArticleResponseDto;
import com.example._spring_blog.article.Entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article articlePostDtoToArticle (ArticlePostDto articlePostDto);
    Article articlePatchDtoToArticle (ArticlePatchDto articlePatchDto);

    ArticleResponseDto articleToArticleResponseDto (Article article);
    List<ArticleResponseDto> articleToArticleResponseDtos (List<Article> article);
}
