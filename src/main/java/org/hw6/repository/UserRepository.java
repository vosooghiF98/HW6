package org.hw6.repository;

import org.hw6.config.DBConfig;
import org.hw6.entity.User;

import java.lang.module.ResolutionException;
import java.sql.*;

public class UserRepository {
    public void save(User user) throws SQLException {
        String query = """
                insert into users (username, nationalcode, birthday, password) 
                values (?,?,?,?);    
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getNationalCode());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
        preparedStatement.setString(4, user.getNationalCode());
        preparedStatement.executeUpdate();
        ResultSet generatedIds = preparedStatement.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt(1);
        user.setId(id);
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

    public void changePass(String userName , String passWord , String newPassword) throws SQLException {
        String query = """
                update users set password = ? where username = ? and password = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,newPassword);
        preparedStatement.setString(2,userName);
        preparedStatement.setString(3,passWord);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
