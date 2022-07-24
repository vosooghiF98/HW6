package org.hw6.service;

import org.hw6.entity.Article;
import org.hw6.entity.User;
import org.hw6.repository.UserArticleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserArticleService {
    UserArticleRepository userArticleRepository = new UserArticleRepository();

    public void loadMyArticles(int userId) throws SQLException {
        ResultSet resultSet = userArticleRepository.loadByUserId(userId);
        if (resultSet.next()) {
            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setBrief(resultSet.getString("brief"));
                article.setContent(resultSet.getString("content"));
                article.setCreateDate(String.valueOf(resultSet.getDate("createdate")));
                article.setPublished(resultSet.getBoolean("ispublished"));
                System.out.println("id : " + article.getId() + "\n" + "title : " + article.getTitle()
                        + "brief : " + article.getBrief() + "content : " + article.getContent()
                        + "create date : " + article.getCreateDate() + "Is published : " + article.isPublished());
            }
            resultSet.close();
        } else {
            System.out.println("You don't have any article!");
        }
    }

    public void editMyArticle(Article article, int userId) throws SQLException {
        userArticleRepository.edit(article,userId);
    }

    public void publish(boolean publish, int userId) throws SQLException {
        userArticleRepository.publish(publish,userId);
    }
}
