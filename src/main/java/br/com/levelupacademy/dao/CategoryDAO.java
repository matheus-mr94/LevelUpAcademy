package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static br.com.levelupacademy.factory.ConnectionFactory.recoverConnection;

public class CategoryDAO {

    private static Connection connection;

    static {
        try {
            connection = recoverConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Category getCategory(Long id) throws SQLException {

        Category category = null;
        String sql = "SELECT * FROM Category WHERE `id` = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String code = resultSet.getString(3);
                    String description = resultSet.getString(4);
                    String studyGuide = resultSet.getString(5);
                    boolean active = resultSet.getBoolean(6);
                    int sequence = resultSet.getInt(7);
                    String urlImage = resultSet.getString(8);
                    String hexCode = resultSet.getString(9);
                    category = new Category
                            (name, code,description,studyGuide,active,sequence,urlImage,hexCode);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
        return category;
    }
}
