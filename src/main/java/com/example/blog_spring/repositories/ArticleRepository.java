package com.example.blog_spring.repositories;

import com.example.blog_spring.entities.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    Article findArticleById(Long id);
    Article findArticlesById(Long id);
    Article findArticlesByAuthor(String author);
}
