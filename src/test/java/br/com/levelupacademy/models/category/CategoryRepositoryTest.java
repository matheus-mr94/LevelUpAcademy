package br.com.levelupacademy.models.category;

import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.util.CategoryBuilder;
import br.com.levelupacademy.models.util.CourseBuilder;
import br.com.levelupacademy.models.util.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAllByActiveTrue__shouldReturnAllActiveCategories() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);

        List<Category> categories = categoryRepository.findAllByActiveTrue();
        assertTrue(categories.size() == 2);
        assertEquals("Programação", categories.get(0).getName());
        assertEquals("DevOps", categories.get(1).getName());
    }

    @Test
    void findByCode__shouldReturnCategoryWhenFindByCode() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);

        String code = "programacao";
        Optional<Category> category = categoryRepository.findByCode(code);
        assertNotNull(category);
        assertEquals("Programação", category.get().getName());
    }

    @Test
    void findByCode__shouldNotReturnCategoryWhenCodeDontExists() {
        String code = "business";
        Optional<Category> category = categoryRepository.findByCode(code);
        assertTrue(category.isEmpty());
    }

    @Test
    void findAllByOrderBySequence__shouldReturnCategoriesOrderedBySequence() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Category categoryDataScience = createCategory("Data Science", "data-science", true, 3);

        List<Category> categories = categoryRepository.findAllByOrderBySequence();
        assertEquals("Programação", categories.get(0).getName());
        assertEquals("DevOps", categories.get(1).getName());
        assertEquals("Data Science", categories.get(2).getName());
    }

    @Test
    void findAllByOrderByNameAsc__shouldReturnCategoriesOrderedByName() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Category categoryDataScience = createCategory("Data Science", "data-science", true, 3);

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        assertEquals("Data Science", categories.get(0).getName());
        assertEquals("DevOps", categories.get(1).getName());
        assertEquals("Programação", categories.get(2).getName());
    }

    @Test
    void countCoursesByCategory__shouldReturnTheCategoryWithMoreCoursesFirstAndTheNumberOfCourses() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Subcategory subcategory = createSubcategory("Java", "java", true, categoryProgramacao);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Java", "java", true, "Nico", subcategory);
        createCourse("Java e OO", "java-oo", true, "Nico", subcategory);
        createCourse("Java Exceptions", "java-exc", true, "Nico", subcategory);
        createCourse("AWS", "aws", true, "Rodrigo", subcategoryDevOps);
        createCourse("Terraform", "terraform", true, "Rodrigo", subcategoryDevOps);

        List<CategoryProjection> categories = categoryRepository.countCoursesByCategory();
        assertEquals("Programação", categories.get(0).getName());
        assertEquals(3, categories.get(0).getCountOfCourses());
    }

    @Test
    void findActiveCategoriesWithPublicCourses__shouldReturnActiveCategoriesThatContainsPublicCourses() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Category categoryBusiness = createCategory("Business", "business", true, 3);
        Subcategory subcategory = createSubcategory("Java", "java", true, categoryProgramacao);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Java", "java", true, "Nico", subcategory);
        createCourse("Java e OO", "java-oo", true, "Nico", subcategory);
        createCourse("Java Exceptions", "java-exc", true, "Nico", subcategory);
        createCourse("AWS", "aws", true, "Rodrigo", subcategoryDevOps);
        createCourse("Terraform", "terraform", true, "Rodrigo", subcategoryDevOps);

        List<Category> categories = categoryRepository.findActiveCategoriesWithPublicCourses();
        assertEquals("Programação", categories.get(0).getName());
        assertEquals("DevOps", categories.get(1).getName());
    }

    @Test
    void findActiveCategoriesWithPublicCourses__shouldNotReturnAnyCategory() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Category categoryBusiness = createCategory("Business", "business", false, 3);
        Subcategory subcategory = createSubcategory("Java", "java", false, categoryProgramacao);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("Java Exceptions", "java-exc", false, "Nico", subcategory);
        createCourse("AWS", "aws", false, "Rodrigo", subcategoryDevOps);

        List<Category> categories = categoryRepository.findActiveCategoriesWithPublicCourses();
        assertTrue(categories.isEmpty());
        assertEquals(0, categories.size());

    }

    @Test
    void findActiveCategoriesWithPublicCoursesByCategoryCode__shouldFindActiveCategoryWithPublicCourse() {
        String code = "devops";
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        Subcategory subcategoryDevOps = createSubcategory("DevOps", "devops", true, categoryDevops);
        createCourse("AWS", "aws", true, "Rodrigo", subcategoryDevOps);

        Optional<Category> category = categoryRepository
                .findActiveCategoriesWithPublicCoursesByCategoryCode(code);
        assertNotNull(categoryDevops);
        assertEquals("DevOps", category.get().getName());

    }

    @Test
    void findActiveCategoriesWithPublicCoursesByCategoryCode__shouldNotFindActiveCategoryWithPublicCourse() {
        String code = "business";
        createCategory("Business", "business", true, 3);

        Optional<Category> category = categoryRepository
                .findActiveCategoriesWithPublicCoursesByCategoryCode(code);
        assertTrue(category.isEmpty());
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