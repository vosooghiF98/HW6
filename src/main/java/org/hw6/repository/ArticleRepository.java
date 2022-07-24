package org.hw6.repository;

import org.hw6.config.DBConfig;
import org.hw6.entity.Article;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRepository {
    public void save(Article article,int userId) throws SQLException {
        String query = """
                insert into articles (title, brief, content, createdate, userid,ispublished) 
                values (?,?,?,?,?,'unpublished');
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,article.getTitle());
        preparedStatement.setString(2,article.getBrief());
        preparedStatement.setString(3,article.getContent());
        preparedStatement.setDate(4, Date.valueOf(article.getCreateDate()));
        preparedStatement.setInt(5,userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet loadByPublished() throws SQLException {
        String query = """
                select title,brief from articles where ispublished = 'published' ;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public ResultSet loadByTitle (String title) throws SQLException {
        String query = """
                select * from articles where title = ? and ispublished = 'published';
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,title);
        return preparedStatement.executeQuery();
    }

}
