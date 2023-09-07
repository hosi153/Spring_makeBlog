package com.example._spring_blog.article.Repository;

import com.example._spring_blog.article.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByEmail(String email);

}
