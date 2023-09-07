package com.example._spring_blog.article.Controller;

import com.example._spring_blog.article.Dto.ArticlePatchDto;
import com.example._spring_blog.article.Dto.ArticlePostDto;
import com.example._spring_blog.article.Entity.Article;
import com.example._spring_blog.article.Mapper.ArticleMapper;
import com.example._spring_blog.article.Repository.ArticleRepository;
import com.example._spring_blog.article.Service.ArticleService;
import com.example._spring_blog.dto.MultiResponseDto;
import com.example._spring_blog.dto.SingleResponseDto;
import com.example._spring_blog.utils.UriCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/")
@Validated
public class ArticleAPIController {
    private final static String ARTICLE_DEFAULT_URL = "/api/article";


    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleRepository articleRepository;

    @PostMapping("articles")
    public ResponseEntity postArticle(@Valid @RequestBody ArticlePostDto articlePostDto){
        Article article = articleMapper.articlePostDtoToArticle(articlePostDto);
        Article createdArticle = articleService.createArticle(article);

        URI location = UriCreator.createUri(ARTICLE_DEFAULT_URL, createdArticle.getArticleId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{article-id}")
    public ResponseEntity patchArticle(@PathVariable("article-id")@Positive long articleId, @Valid @RequestBody ArticlePatchDto articlePatchDto){
        articlePatchDto.setArticleId(articleId);
        Article article = articleService.updateArticle(articleMapper.articlePatchDtoToArticle(articlePatchDto));

        return new ResponseEntity(
                new SingleResponseDto<>(articleMapper.articleToArticleResponseDto(article)), HttpStatus.OK
        );
    }

    @GetMapping("{article-id}")
    public ResponseEntity getArticle(@PathVariable("article-id")@Positive long articleId){
        Article article = articleService.findArticle(articleId);
        return new ResponseEntity(
                new SingleResponseDto<>(articleMapper.articleToArticleResponseDto(article)),HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity getArticles (@Valid @RequestBody int page,@Valid @RequestBody int size){
        Page<Article> pageArticle = articleService.findArticles(page-1,size);
        List<Article> articles = pageArticle.getContent();
        return new ResponseEntity(
                new MultiResponseDto<>(articleMapper.articleToArticleResponseDtos(articles),pageArticle),HttpStatus.OK
        );
    }

    @DeleteMapping("{article-id}")
    public ResponseEntity deleteArticle (@PathVariable("article-id")@Positive long articleId){
        articleService.deleteArticle(articleId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
