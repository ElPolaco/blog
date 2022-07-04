package com.example.blog_spring.repositories;

import com.example.blog_spring.entities.Article;
import com.example.blog_spring.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    Iterable<Comment> findAllByArticle(Article a);
}
