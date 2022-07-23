package org.hw6.repository;

import org.hw6.config.DBConfig;
import org.hw6.entity.Article;

import java.awt.geom.RectangularShape;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRepository {
    public void save(Article article) throws SQLException {
        String query = """
                insert into articles (title, brief, content, createdate, userid) 
                values (?,?,?,?,?);
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,article.getTitle());
        preparedStatement.setString(2,article.getBrief());
        preparedStatement.setString(3,article.getContent());
        preparedStatement.setDate(4, Date.valueOf(article.getCreateDate()));
        preparedStatement.setInt(5,article.getUser().getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet loadByPublished() throws SQLException {
        String query = """
                select title,brief from articles where ispublished = true;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public ResultSet loadById (int id) throws SQLException {
        String query = """
                select * from articles where id = ? and ispublished =true;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public void publish(boolean publish) throws SQLException {
        String query = """
                update articles set ispublished = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,publish);
    }

}
