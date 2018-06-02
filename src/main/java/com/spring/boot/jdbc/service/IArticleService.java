package com.spring.boot.jdbc.service;

import java.util.List;

import com.spring.boot.jdbc.entity.Article;



public interface IArticleService {
	
     List<Article> getAllArticles();
     Article getArticleById(int articleId);
     boolean addArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(int articleId);
     
}
