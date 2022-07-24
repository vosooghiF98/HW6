package org.hw6.service;

import org.hw6.entity.Article;
import org.hw6.entity.User;
import org.hw6.repository.ArticleRepository;
import org.hw6.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepository();

    public void loadAll(User user) throws SQLException {
        ResultSet resultSet = articleRepository.loadByPublished();
        while (resultSet.next()) {
            Article article = new Article();
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            System.out.println("Title : " + article.getTitle() + "\n" + "Brief : " + article.getBrief());
        }
        resultSet.close();
    }

    public Article loadByTitle(User user, String title) throws SQLException {
        ResultSet resultSet = articleRepository.loadByTitle(title);
        if (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            article.setContent(resultSet.getString("content"));
            article.setCreateDate(resultSet.getString("createdate"));
            article.setPublished(resultSet.getBoolean("ispublished"));
            resultSet.close();
            return article;
        }
        return null;
    }

    public void save(Article article) throws SQLException {
        articleRepository.save(article);
    }


}
