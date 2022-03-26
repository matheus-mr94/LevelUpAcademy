package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.util.JPAUtil;
import br.com.levelupacademy.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryDAOTest {

    private CategoryDAO categoryDAO;
    private EntityManager em;

    @BeforeEach
    public void initializeTransaction() {
        this.em = JPAUtil.getEntityManager();
        this.categoryDAO = new CategoryDAO(em);
        this.em.getTransaction().begin();
    }

    @AfterEach
    public void rollbackTransaction() {
        this.em.getTransaction().rollback();
    }

    @Test
    void findActiveCategoriesOrderedBySequence__should_return_active_categories_in_sequence() {
        Category programacaoCategory = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withDescription("Cursos de programação")
                .withActive(true)
                .withSequence(1)
                .withUrlImage("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png")
                .withHexCode("#f16165")
                .create();

        Category devopsCategory = new CategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withDescription("Cursos de DevOps")
                .withActive(true)
                .withSequence(2)
                .withUrlImage("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png")
                .withHexCode("#f16165")
                .create();

        Category dataScienceCategory = new CategoryBuilder()
                .withName("Data Science")
                .withCode("data-science")
                .withDescription("Cursos de data science")
                .withActive(false)
                .withSequence(3)
                .withUrlImage("https://www.alura.com.br/assets/api/formacoes/categorias/data-science.svg")
                .withHexCode("#9cd33b")
                .create();

        categoryDAO.create(programacaoCategory);
        categoryDAO.create(devopsCategory);
        categoryDAO.create(dataScienceCategory);

        List<Category> activeCategoriesInSequence = categoryDAO.findActiveCategoriesOrderedBySequence();

        assertNotNull(activeCategoriesInSequence);
        assertEquals(2, activeCategoriesInSequence.size());
        assertEquals("Programação", activeCategoriesInSequence.get(0).getName());
        assertEquals("DevOps", activeCategoriesInSequence.get(1).getName());


    }
}