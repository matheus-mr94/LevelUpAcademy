package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.util.JPAUtil;
import br.com.levelupacademy.util.builder.CategoryBuilder;
import br.com.levelupacademy.util.builder.CourseBuilder;
import br.com.levelupacademy.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOTest {

    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private CourseDAO courseDAO;
    private EntityManager em;
    private Category category;
    private Subcategory subcategory;

    @BeforeEach
    public void initializeTransaction() {
        this.em = JPAUtil.getEntityManager();
        this.categoryDAO = new CategoryDAO(em);
        this.subcategoryDAO = new SubcategoryDAO(em);
        this.courseDAO = new CourseDAO(em);
        em.getTransaction().begin();

        category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withDescription("Cursos de programação")
                .withActive(true)
                .withSequence(1)
                .withUrlImage("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png")
                .withHexCode("#f16165")
                .create();

        em.persist(category);

         subcategory = new SubcategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("Cursos de java")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        em.persist(subcategory);
    }

    @AfterEach
    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Test
    void findPublicCourses__should_return_public_courses() {
        Course course = new CourseBuilder()
                .withName("Java")
                .withCode("java")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(subcategory)
                .create();

        Course course1 = new CourseBuilder()
                .withName("Java e exceptions")
                .withCode("java-e-exceptions")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(false)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(subcategory)
                .create();

        em.persist(course);
        em.persist(course1);

        List<Course> publicCourses = courseDAO.findPublicCourses();

        assertNotNull(publicCourses);
        assertEquals(1, publicCourses.size());

    }

    @Test
    void updateCourseToPublicWithJPA__should_update_course_visibility() {

        Course course = new CourseBuilder()
                .withName("Java")
                .withCode("java")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(subcategory)
                .create();

        Course course1 = new CourseBuilder()
                .withName("Java e exceptions")
                .withCode("java-e-exceptions")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(false)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(subcategory)
                .create();

        em.persist(course);
        em.persist(course1);

        courseDAO.updateCourseToPublicWithJPA();
        assertTrue(course1.isVisible());
    }
}