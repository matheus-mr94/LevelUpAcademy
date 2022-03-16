package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.course.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubcategoryDAO {

    private static Connection connection;

    public SubcategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public static Long getSubcategoryId(Course course) {

        String sql = "SELECT `id` FROM Subcategory WHERE `code` = ?";
        Long subcategoryId = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getSubcategoryCode());
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    subcategoryId = resultSet.getLong(1);
                }
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return subcategoryId;
    }
}
