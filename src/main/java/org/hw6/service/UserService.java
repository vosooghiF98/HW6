package org.hw6.service;

import jdk.jfr.consumer.RecordedStackTrace;
import org.hw6.entity.Article;
import org.hw6.entity.User;
import org.hw6.repository.ArticleRepository;
import org.hw6.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    UserRepository userRepository = new UserRepository();
    ArticleRepository articleRepository = new ArticleRepository();

    public void save(User user) throws SQLException {
        userRepository.save(user);
        for (int i = 0; i < user.getArticles().size() ; i++) {
            articleRepository.save(user.getArticles().get(i));
        }
    }

    public boolean enter (String userName , String password) throws SQLException {
        ResultSet resultSet = userRepository.loadByUserAndPass(userName,password);
        if (resultSet.next()){
            System.out.println("Welcome!");
            return true;
        }
        System.out.println("Please Sign Up First!");
        User user = new User();
        save(user);
        return false;
    }

    public void loadAll (User user) throws SQLException {
        if (enter(user.getUserName(),user.getPassword())){
            ResultSet resultSet = articleRepository.loadByPublished();
            while (resultSet.next()){
                Article article = new Article();
                article.setTitle(resultSet.getString("title"));
                article.setBrief(resultSet.getString("brief"));
                System.out.println("Title : "+article.getTitle()+"\n"+"Brief : "+article.getBrief());
            }
            resultSet.close();
        }
    }

    public Article loadByTitle (User user,String title) throws SQLException {
        if (enter(user.getUserName(),user.getPassword())){
            ResultSet resultSet = articleRepository.loadByTitle(title);
            if (resultSet.next()){
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
        }
        return null;
    }

}
