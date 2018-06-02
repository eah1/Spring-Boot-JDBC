package com.spring.boot.jdbc.dao;
import java.util.List;

import com.spring.boot.jdbc.entity.Article;


public interface IArticleDAO {
	
    public List<Article> getAllArticles();
    public Article getArticleById(int articleId);
    public void addArticle(Article article);
    public void updateArticle(Article article);
    public void deleteArticle(int articleId);
    public boolean articleExists(String title, String category);
    
}
 