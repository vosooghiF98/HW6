package org.hw6.service;

import org.hw6.entity.Article;
import org.hw6.repository.ArticleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepository();

    public void loadAll() throws SQLException {
        ResultSet resultSet = articleRepository.loadByPublished();
        while (resultSet.next()) {
            Article article = new Article();
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            System.out.println("Title : " + article.getTitle() + "\n" + "Brief : " + article.getBrief());
        }
        resultSet.close();
    }

    public void loadByTitle(String title) throws SQLException {
        ResultSet resultSet = articleRepository.loadByTitle(title);
        if (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            article.setContent(resultSet.getString("content"));
            article.setCreateDate(resultSet.getString("createdate"));
            resultSet.close();
            System.out.println("id : " + article.getId() + "\n" + "title : " + article.getTitle() + "\n"
                    + "brief : " + article.getBrief() + "\n" + "content : " + article.getContent() + "\n" + "create date : " + article.getCreateDate());
        } else {
            resultSet.close();
            System.out.println("This article not exist!");
        }
    }

    public void save(Article article, int userId) throws SQLException {
        articleRepository.save(article, userId);
    }


}
