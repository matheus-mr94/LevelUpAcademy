package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.util.CategoryBuilder;
import br.com.levelupacademy.models.util.CourseBuilder;
import br.com.levelupacademy.models.util.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void getInstructorWithMoreCourses__shouldReturnInstructorWithMoreCourses() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Subcategory subcategory = createSubcategory("Java", "java", true, categoryProgramacao);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Java", "java", true, "Nico", subcategory);
        createCourse("Java e OO", "java-oo", true, "Nico", subcategory);
        createCourse("Java Exceptions", "java-exc", true, "Nico", subcategory);
        createCourse("AWS", "aws", true, "Rodrigo", subcategoryDevOps);
        createCourse("Terraform", "terraform", true, "Rodrigo", subcategoryDevOps);

        InstructorProjection projection = courseRepository.getInstructorWithMoreCourses();
        assertEquals("Nico", projection.getInstructor());
        assertEquals(3, projection.getTotalOfCourses());
    }

    @Test
    void findAllBySubcategory__shouldReturnAPageWithCoursesBySubcategory() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Subcategory subcategory = createSubcategory("Java", "java", true, categoryProgramacao);
        createCourse("Java", "java", true, "Nico", subcategory);
        createCourse("Java e OO", "java-oo", true, "Nico", subcategory);

        PageRequest pageRequest = PageRequest.of(0,1 );

        Page<Course> page = courseRepository.findAllBySubcategory(subcategory, pageRequest);

        assertEquals(2, page.getTotalPages());
        assertEquals(1, page.getSize());
        assertEquals(2, page.getTotalElements());

        pageRequest = PageRequest.of(0,2 );
        page = courseRepository.findAllBySubcategory(subcategory, pageRequest);

        assertEquals(1, page.getTotalPages());
    }

    @Test
    void findByCode__shouldFindCourseByCode() {
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Terraform", "terraform", true, "Rodrigo", subcategoryDevOps);

        String code = "terraform";
        Optional<Course> course = courseRepository.findByCode(code);
        assertNotNull(course);
        assertEquals("Terraform", course.get().getName());
    }

    @Test
    void findByCode__shouldNotReturnCourseWhenCodeDontExists() {
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Terraform", "terraform", true, "Rodrigo", subcategoryDevOps);

        String code = "kotlin";
        Optional<Course> course = courseRepository.findByCode(code);
        assertTrue(course.isEmpty());
    }

    public Category createCategory(String name, String code, boolean active, int sequence) {
        Category category = new CategoryBuilder()
                .withName(name)
                .withCode(code)
                .withDescription("Descrição")
                .withActive(active)
                .withSequence(sequence)
                .withUrlImage("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png")
                .withHexCode("#f16165")
                .create();

        entityManager.persist(category);
        return category;

    }

    public Subcategory createSubcategory(String name, String code, boolean active, Category category) {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName(name)
                .withCode(code)
                .withDescription("Descrição")
                .withActive(active)
                .withSequence(1)
                .withCategory(category)
                .create();

        entityManager.persist(subcategory);
        return subcategory;
    }

    public Course createCourse(String name, String code, boolean active, String instructor, Subcategory subcategory) {
        Course course = new CourseBuilder()
                .withName(name)
                .withCode(code)
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(active)
                .withInstructor(instructor)
                .withSyllabus("syllabus")
                .withDevelopedSkills("devoloped skills")
                .withSubcategory(subcategory)
                .create();

        entityManager.persist(course);
        return course;
    }
}