package org.hw6.service;

import org.hw6.entity.User;
import org.hw6.repository.UserRepository;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public Boolean save(User user) throws SQLException {
        ResultSet resultSet = userRepository.checkSave(user.getNationalCode());
        if (!resultSet.next()) {
            userRepository.save(user);
            System.out.println("Your password is your national code.");
            System.out.println("Sign Up was successful.");
            resultSet.close();
            return true;
        }else {
            System.out.println("You have already registered!");
            resultSet.close();
            return false;
        }
    }

    public User enter(String userName, String password) throws SQLException {
        ResultSet resultSet = userRepository.loadByUserAndPass(userName, password);
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
            return user;
        } else {
            resultSet.close();
            return null;
        }
    }

    public void changePass(String userName,String passWord ,String newPass) throws SQLException {
        userRepository.changePass(userName,passWord,newPass);
    }



}
