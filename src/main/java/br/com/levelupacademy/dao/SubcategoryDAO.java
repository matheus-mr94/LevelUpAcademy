package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static br.com.levelupacademy.dao.CategoryDAO.getCategory;
import static br.com.levelupacademy.factory.ConnectionFactory.recoverConnection;

public class SubcategoryDAO {

    private static Connection connection;
    private EntityManager em;

    public SubcategoryDAO(EntityManager em) {
        this.em = em;
    }

    static {
        try {
            connection = recoverConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Subcategory getSubcategoryWithJPA(String code) {
        String jpql = "SELECT s FROM Subcategory s WHERE s.code = :code";
        return this.em.createQuery(jpql, Subcategory.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    public List<Subcategory> findSubcategoriesWithoutDescription() {
        String jpql = "SELECT s FROM Subcategory s WHERE s.description = '' OR s.description = NULL";
        return this.em.createQuery(jpql, Subcategory.class).getResultList();
    }

    public List<Subcategory> findActiveSubcategoriesAndPutInSequence() {
        String jpql = "SELECT s FROM Subcategory s WHERE active = true ORDER BY sequence";
        return this.em.createQuery(jpql, Subcategory.class).getResultList();
    }

    public static Subcategory getSubcategory(String subcategoryCode) {
        String sql = "SELECT * FROM Subcategory WHERE `code` = ?";
        Subcategory subcategory = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, subcategoryCode);
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    String name = resultSet.getString(2);
                    String code = resultSet.getString(3);
                    String description = resultSet.getString(4);
                    String studyGuide = resultSet.getString(5);
                    boolean active = resultSet.getBoolean(6);
                    int sequence = resultSet.getInt(7);
                    Long categoryId = resultSet.getLong(8);
                    Category category = getCategory(categoryId);
                    subcategory = new Subcategory
                           (name, code, description, studyGuide, active, sequence, category);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return subcategory;
    }

    public static Long getSubcategoryId(Course course) throws SQLException {

        String sql = "SELECT `id` FROM Subcategory WHERE `code` = ?";
        Long subcategoryId = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, course.getSubcategoryCode());
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    subcategoryId = resultSet.getLong(1);
                }
            }
            connection.commit();
        } catch ( SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
        return subcategoryId;
    }
}
