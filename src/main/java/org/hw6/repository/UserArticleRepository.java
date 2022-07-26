package org.hw6.repository;

import org.hw6.config.DBConfig;
import org.hw6.entity.Article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserArticleRepository {
    public ResultSet loadByUserId (int id) throws SQLException {
        String query = """
                select * from articles where userid = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public void edit(Article article, int userId,int id) throws SQLException {
        String query = """
                update articles set title = ? , brief = ? , content = ? , ispublished = ? where id = ? and userid = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,article.getTitle());
        preparedStatement.setString(2, article.getBrief());
        preparedStatement.setString(3,article.getContent());
        preparedStatement.setString(4,article.getIsPublished());
        preparedStatement.setInt(5,id);
        preparedStatement.setInt(6,userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void publish(String publish ,int id , int userId) throws SQLException {
        String query = """
                update articles set ispublished = ? where id = ? and userid = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,publish);
        preparedStatement.setInt(2,id);
        preparedStatement.setInt(3,userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet checkEdit(int id, int userId) throws SQLException {
        String query = """
                select * from articles where id = ? and userid = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,userId);
        return preparedStatement.executeQuery();
    }
}
