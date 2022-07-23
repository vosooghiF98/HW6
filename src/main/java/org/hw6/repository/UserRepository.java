package org.hw6.repository;

import org.hw6.config.DBConfig;
import org.hw6.entity.User;

import java.lang.module.ResolutionException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public void save(User user) throws SQLException {
        String query = """
                insert into users (username, nationalcode, birthday, password) 
                values (?,?,?,?);    
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getNationalCode());
        preparedStatement.setDate(3, user.getBirthday());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet loadByUserAndPass(String userName, String password) throws SQLException {
        String query = """
                select * from users where username = ? and password = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }
}
