package com.spring.boot.jdbc.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article mapRow(ResultSet arg0, int arg1) throws SQLException {
			
		Article article = new Article();
		
		article.setArticleId(arg0.getInt("articleID"));
		article.setTitle(arg0.getString("title"));
		article.setCategory(arg0.getString("category"));
		
		return article;
		
	}

}
