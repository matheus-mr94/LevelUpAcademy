package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.util.CategoryBuilder;
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
class SubcategoryRepositoryTest {

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findByCode__shouldFindSubcategoryByCode() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        createSubcategory("Java", "java", true, categoryProgramacao);
        String code = "java";
        Optional<Subcategory> subcategory = subcategoryRepository.findByCode(code);
        assertNotNull(subcategory);
        assertEquals("Java", subcategory.get().getName());

    }

    @Test
    void findByCode__shouldNotReturnSubcategoryWhenCodeDontExists() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        createSubcategory("Java", "java", true, categoryProgramacao);
        String code = "kotlin";
        Optional<Subcategory> subcategory = subcategoryRepository.findByCode(code);
        assertTrue(subcategory.isEmpty());
    }

    @Test
    void findAllByOrderByNameAsc__shoudlReturnSubcategoriesOrderedByName() {
        Category categoryProgramacao = createCategory("Programação", "programacao", true, 1);
        Category categoryDevops = createCategory("DevOps", "devops", true, 2);
        createSubcategory("Java", "java", true, categoryProgramacao);
        createSubcategory("DevOps", "devops", true, categoryDevops);

        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByNameAsc();
        assertEquals("DevOps", subcategories.get(0).getName());
        assertEquals("Java", subcategories.get(1).getName());
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
}