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
        while (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            article.setContent(resultSet.getString("content"));
            article.setCreateDate(String.valueOf(resultSet.getDate("createdate")));
            article.setPublished(resultSet.getBoolean("ispublished"));
            System.out.println("id : " + article.getId() + "\n" + "title : " + article.getTitle() + "\n"
                    + "brief : " + article.getBrief() + "\n" +  "content : " + article.getContent() + "\n"
                    + "create date : " + article.getCreateDate() + "\n" + "Is published : " + article.isPublished());
        }
        resultSet.close();

    }

    public void editMyArticle(Article article, int userId,int id) throws SQLException {
        userArticleRepository.edit(article, userId,id);
    }

    public void publish(boolean publish, int userId) throws SQLException {
        userArticleRepository.publish(publish, userId);
    }
}
