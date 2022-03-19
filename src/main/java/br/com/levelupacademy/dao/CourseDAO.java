package br.com.levelupacademy.dao;

import br.com.levelupacademy.dto.CourseDTO;
import br.com.levelupacademy.models.course.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.levelupacademy.dao.SubcategoryDAO.getSubcategoryId;

public class CourseDAO {

    private static Connection connection;
    private EntityManager em;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    public  void insertCourseWithJPA(Course course) {
        this.em.persist(course);
        Long id = course.getId();
        System.out.println("Curso criado com o ID: " + id);
    }

    public void deleteCourseWithJPA(String code) {
        String jpql = "SELECT c FROM Course c WHERE c.code= :code";
        Course course = this.em.createQuery(jpql, Course.class)
                .setParameter("code", code)
                .getSingleResult();
        this.em.merge(course);
        this.em.remove(course);
    }

    public List<Course> findPublicCourses() {
        String jpql = "SELECT c FROM Course c WHERE visible = true";
        return this.em.createQuery(jpql, Course.class).getResultList();
    }

    public void updateCourseToPublicWithJPA() {
        String jpql = "UPDATE Course SET visible = true WHERE visible = false";
        int i = this.em.createQuery(jpql).executeUpdate();
        System.out.println("Course(s) updated = " + i);
    }

    public static void insertCourse(Course course) throws SQLException {

        String sql = """
                INSERT INTO 
                `Course`(`name`, `code`, estimated_time_in_hours, visible, target, instructor ,syllabus, 
                developed_skills, subcategory_id)
                VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?);
                                    
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
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

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    course.setId(resultSet.getLong(1));
                    System.out.println("O ID do curso criado é: " + course.getId());
                }

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void deleteCourse(String code) throws SQLException {
        String sql = "DELETE FROM Course WHERE `code` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, code);
            preparedStatement.execute();

            Integer rowsAffected = preparedStatement.getUpdateCount();
            System.out.println("Course was removed with success, rows affected: " + rowsAffected);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void updateCourseToPublic() throws SQLException {
        String sql = "UPDATE Course SET visible = true WHERE visible = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();

            Integer rowsAffected = preparedStatement.getUpdateCount();
            System.out.println("Cursos atualizados: " + rowsAffected);
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<CourseDTO> report() throws SQLException {
        String sql = "SELECT c.`id`, c.`name`, c.estimated_time_in_hours, c.subcategory_id, s.`name` " +
                "FROM Course c\n" +
                "INNER JOIN Subcategory s  ON c.subcategory_id = s.`id` WHERE `visible`;";

        List<CourseDTO> courseDTOList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String course = resultSet.getString(2);
                    Integer estimatedTimeInHours = resultSet.getInt(3);
                    Long subcategoryId = resultSet.getLong(4);
                    String subcategory = resultSet.getString(5);
                    CourseDTO courseDTO = new CourseDTO(id, course, estimatedTimeInHours,subcategoryId,subcategory);
                    courseDTOList.add(courseDTO);
                }
            }
          connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }
        return courseDTOList;
    }
}