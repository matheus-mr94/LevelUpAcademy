package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.util.CategoryBuilder;
import br.com.levelupacademy.models.util.CourseBuilder;
import br.com.levelupacademy.models.util.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
@Transactional
class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_returnStatusOkAndMessage() throws Exception {
            ResultActions resultAction = mockMvc
                    .perform(get("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
                            .accept(MediaType.APPLICATION_JSON));
            resultAction.andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Cache foi limpo"));
    }

    @Test
    void completeResponse__shouldReturnActiveCategoriesWithPublicCourses() throws Exception {
        Category category = createCategory("Programação", "programacao", true, 1);
        Subcategory subcategory = createSubcategory("Java", "java", true, category);
        Course courseJava = createCourse("Java", "java", true, "Nico", subcategory);
        Course courseJavaOO = createCourse("Java e OO", "java-oo", true, "Nico", subcategory);

        category.setSubcategories(Arrays.asList(subcategory));
        subcategory.setCourses(Arrays.asList(courseJava, courseJavaOO));

        ResultActions resultAction = mockMvc
                .perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON));

        resultAction.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Programação"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("programacao"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subcategoryList[0].name").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subcategoryList[0].code").value("java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subcategoryList[0].courseResponseList[0].name").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subcategoryList[0].courseResponseList[1].name").value("Java e OO"));
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