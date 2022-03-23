package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static br.com.levelupacademy.factory.ConnectionFactory.recoverConnection;

public class CategoryDAO {

    private static Connection connection;
    private EntityManager em;

    static {
        try {
            connection = recoverConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public List<Category> findActiveCategoriesAndPutInSequence() {
        String jpql = "SELECT c FROM Category c WHERE c.active = true ORDER BY c.sequence";
        try {
            List<Category> resultList = this.em.createQuery(jpql, Category.class).getResultList();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void create(Category category) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(category);
            this.em.getTransaction().commit();
        }  catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAll() {
        String jpql = "DELETE FROM Category";
        try {
            this.em.getTransaction().begin();
            this.em.createQuery(jpql).executeUpdate();
            this.em.getTransaction().commit();
        }  catch (Exception e) {
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
