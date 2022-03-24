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
        try{
            this.em.getTransaction().begin();
            this.em.persist(course);
            this.em.getTransaction().commit();
            Long id = course.getId();
            System.out.println("Curso criado com o ID: " + id);
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteCourseWithJPA(String code) {
        try {
            this.em.getTransaction().begin();
            String jpql = "SELECT c FROM Course c WHERE c.code= :code";
            Course course = this.em.createQuery(jpql, Course.class)
                    .setParameter("code", code)
                    .getSingleResult();
            this.em.merge(course);
            this.em.remove(course);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Course> findPublicCourses() {
        try{
            this.em.getTransaction().begin();
            String jpql = "SELECT c FROM Course c WHERE c.visible = true";
            List<Course> resultList = this.em.createQuery(jpql, Course.class).getResultList();
            this.em.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateCourseToPublicWithJPA() {
        try {
            this.em.getTransaction().begin();
            String jpql = "UPDATE Course SET visible = true WHERE visible = false";
            int i = this.em.createQuery(jpql).executeUpdate();
            this.em.getTransaction().commit();
            System.out.println("Course(s) updated = " + i);
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Course findCourseByCode(String code) {
        try {
            this.em.getTransaction().begin();
            String jpql = "SELECT new br.com.levelupacademy.models.course.Course(" +
                    "c.name, c.code, c.estimatedTimeInHours, " +
                    "c.target, c.visible, c.instructor, " +
                    "c.syllabus, c.developedSkills, c.subcategory) FROM Course c WHERE c.code = :code";
            Course course = em.createQuery(jpql, Course.class)
                    .setParameter("code", code)
                    .getSingleResult();
            em.getTransaction().commit();
            return course;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void create(Course course) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(course);
            this.em.getTransaction().commit();

        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAll() {
        String jpql = "DELETE FROM Course";
        try {
            em.getTransaction().begin();
            em.createQuery(jpql).executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
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