package com.example.blog_spring;

import com.example.blog_spring.entities.Article;
import com.example.blog_spring.entities.Comment;
import com.example.blog_spring.repositories.ArticleRepository;
import com.example.blog_spring.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
public class RESTController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping("/hello/{p}")
    public String Hello(@PathVariable String p){
        return "Hello, "+p+"!";
    }
    @RequestMapping("/")
    public ModelAndView Index(Model model){
        ModelAndView m =new ModelAndView("index.html");
        model.addAttribute("articles",articleRepository.findAll());
        return m;
    }
    @RequestMapping("/read/{id}")
    public ModelAndView ReadArticle(@PathVariable Long id, Model model){
        Article a = articleRepository.findArticleById(id);
        model.addAttribute("headline",a.getHeadline());
        model.addAttribute("author",a.getAuthor());
        model.addAttribute("date",a.getDate().toString());
        model.addAttribute("txt",a.getTxt());
        model.addAttribute("id",id);
        model.addAttribute("comments",commentRepository.findAllByArticle(a));
        return new ModelAndView("read.html");
    }
    @PostMapping("/write_comment")
    public ModelAndView WriteComment(@RequestParam Long article_id,@RequestParam String author, @RequestParam String content, ModelMap model){
        Comment c = new Comment();
        c.setAuthor(author);
        c.setContent(content);
        c.setArticle(articleRepository.findArticleById(article_id));
        c.setDate(new Date());
        commentRepository.save(c);
        return new ModelAndView("redirect:/read/"+article_id.toString());
    }
    @RequestMapping("/about")
    public ModelAndView AboutUs(){
        return new ModelAndView("about.html");
    }
    @RequestMapping("/write_article")
    public ModelAndView WriteArticle(){
        return new ModelAndView("write_article.html");
    }
    @PostMapping("/add_article")
    public String AddArticle(@RequestParam String headline,@RequestParam String txt, @RequestParam String author){
        Article a = new Article();
        a.setHeadline(headline);
        a.setTxt(txt);
        a.setAuthor(author);
        a.setDate(new Date());
        articleRepository.save(a);
        return "Article published successfully";
    }

}