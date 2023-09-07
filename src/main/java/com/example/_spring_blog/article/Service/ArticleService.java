package com.example._spring_blog.article.Service;

import com.example._spring_blog.article.Entity.Article;
import com.example._spring_blog.article.Repository.ArticleRepository;
import com.example._spring_blog.exception.BusinessLogicException;
import com.example._spring_blog.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService (ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public Article createArticle (Article article){
        verifyExistsEmail(article.getEmail());
        Article saveArticle = articleRepository.save(article);
        return saveArticle;
    }

    public Article updateArticle ( Article article){
        Article findArticle = findVerifiedArticle(article.getArticleId());
        Optional.ofNullable(article.getArticleTitle()).ifPresent(articleTitle -> findArticle.setArticleTitle(articleTitle));
        Optional.ofNullable(article.getContent()).ifPresent(content -> findArticle.setContent(content));
        return articleRepository.save(findArticle);
    }

    public Article findArticle ( long articleId){
        return findVerifiedArticle(articleId);
    }

    public Page<Article> findArticles(int page,int size){
        return articleRepository.findAll(PageRequest.of(page,size, Sort.by("articleId").descending()));
    }

    public void deleteArticle(long articleId){
        Article article = findVerifiedArticle(articleId);
        articleRepository.delete(article);
    }



    @Transactional(readOnly = true)
    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalMember =
                articleRepository.findById(articleId);
        Article findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Article> member =  articleRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.ARTICL_EXISTS);
    }
}
