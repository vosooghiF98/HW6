package org.hw6.service;

import org.hw6.entity.Article;
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
            article.setPublished(resultSet.getString("ispublished"));
            System.out.println("id : " + article.getId() + "\n" + "title : " + article.getTitle() + "\n"
                    + "brief : " + article.getBrief() + "\n" + "content : " + article.getContent() + "\n"
                    + "create date : " + article.getCreateDate() + "\n" + "Is published : " + article.getIsPublished());
        }
        resultSet.close();

    }

    public void editMyArticle(Article article, int userId, int id) throws SQLException {
        userArticleRepository.edit(article, userId, id);
    }

    public void publish(String publish, int id, int userId) throws SQLException {
        userArticleRepository.publish(publish, id, userId);
    }

    public boolean checkEdit(int id, int userId) throws SQLException {
        ResultSet resultSet = userArticleRepository.checkEdit(id, userId);
        if (resultSet.next()) {
            resultSet.close();
            return true;
        } else {
            System.out.println("The selected article is not for you!");
            resultSet.close();
            return false;
        }
    }
}
