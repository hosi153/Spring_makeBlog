package com.example._spring_blog.article.Controller;


import com.example._spring_blog.article.Entity.Article;
import com.example._spring_blog.article.Repository.ArticleRepository;
import com.example._spring_blog.article.Service.ArticleService;
import com.example._spring_blog.member.Entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Positive;
import java.util.List;

@Controller
public class ArticleViewController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;


    @GetMapping("/articles")
    public String getArticles(Model model){
        List<Article> rs = articleRepository.findAll();

        model.addAttribute("articles",rs);
        return "articleList";
    }

    @GetMapping("/articles/{article-id}/update")
    public String updateArticle(@PathVariable("article-id")@Positive long articleId, Model model){
        articleService.updateArticle(articleService.findArticle(articleId));
        return "updateArticle";
    }

    @GetMapping("/articles/{article-id}")
    public String getArticle(@PathVariable("article-id")long articleId,Model model){
        Article article = articleService.findArticle(articleId);
        model.addAttribute("article",article);
        return "article_view";
    }

    @GetMapping("/new-article")
    public  String newArticle(Model model){
        return "newArticle";
    }

    @GetMapping("/articles/{article-id}/delete")
    public String deleteMember(@PathVariable("article-id") @Positive long articleId){

        articleService.deleteArticle(articleId);


        return "articleList";
    }



}
