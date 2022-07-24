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

    public void save(User user) throws SQLException {
        userRepository.save(user);
    }

    public boolean enter(String userName, String password) throws SQLException {
        ResultSet resultSet = userRepository.loadByUserAndPass(userName, password);
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
            return true;
        } else {
            return false;
        }
    }

    public void changePass(String userName,String passWord ,String newPass) throws SQLException {
        userRepository.changePass(userName,passWord,newPass);
    }



}
