package com.spring.boot.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.jdbc.entity.ArticleRowMapper;
import com.spring.boot.jdbc.entity.Article;

@Transactional
@Repository
public class ArticleDAO implements IArticleDAO{
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;

	public ArticleDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Article> getAllArticles() {
		
		String sql = "SELECT articleID, title, category FROM articles";
		RowMapper<Article> rowMapper = new ArticleRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
		
	}

	@Override
	public Article getArticleById(int articleId) {
		
		String sql = "SELECT articleID, title, category FROM articles WHERE articleId = ?";
		RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
		Article article = jdbcTemplate.queryForObject(sql, rowMapper, articleId);
		return article;
		
	}

	@Override
	public void addArticle(Article article) {
		
		//Add article
		String sql = "INSERT INTO articles (articleID, title, category) values (?, ?, ?)";
		jdbcTemplate.update(sql, article.getArticleId(), article.getTitle(), article.getCategory());
				
		//Fetch article id
		sql = "SELECT articleID FROM articles WHERE title = ? and category=?";
		int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());
				
		//Set article id 
		article.setArticleId(articleId);
		
	}

	@Override
	public void updateArticle(Article article) {
		
		String sql = "UPDATE articles SET title=?, category=? WHERE articleId=?";
		jdbcTemplate.update(sql, article.getTitle(), article.getCategory(), article.getArticleId());
		
	}

	@Override
	public void deleteArticle(int articleId) {
		
		String sql = "DELETE FROM articles WHERE articleId=?";
		jdbcTemplate.update(sql, articleId);
		
	}

	@Override
	public boolean articleExists(String title, String category) {
		
		String sql = "SELECT count(*) FROM articles WHERE title = ? and category=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);
		
		return (count == 0) ? false : true;
		
	}
	
}
