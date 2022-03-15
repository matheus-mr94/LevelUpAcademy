package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.course.Course;

import java.sql.*;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertCourse(Course course) {

        String sql = """
                    INSERT INTO 
                    `Course`(`name`, `code`, estimated_time_in_hours, visible, target, instructor ,syllabus, 
                    developed_skills, subcategory_id)
                    VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    
                    """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getCode());
            preparedStatement.setInt(3, course.getEstimatedTimeInHours());
            preparedStatement.setBoolean(4, course.isVisible());
            preparedStatement.setString(5, course.getTarget());
            preparedStatement.setString(6, course.getInstructor());
            preparedStatement.setString(7, course.getSyllabus());
            preparedStatement.setString(8, course.getDevelopedSkills());
            preparedStatement.setLong(9, getSubcategoryId(course));

            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    course.setId(resultSet.getLong(1));
                    System.out.println("O ID do curso criado é: " + course.getId());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(String code) {
        String sql = "DELETE FROM Course WHERE `code` = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            preparedStatement.execute();
            System.out.println("Course was removed with success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getSubcategoryId(Course course) {

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
