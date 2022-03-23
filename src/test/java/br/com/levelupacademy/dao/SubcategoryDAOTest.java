package br.com.levelupacademy.dao;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.util.JPAUtil;
import br.com.levelupacademy.util.builder.CategoryBuilder;
import br.com.levelupacademy.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubcategoryDAOTest {

    private SubcategoryDAO subcategoryDAO;
    private EntityManager em;
    private Category category;

    @BeforeEach
    public void initializeTransaction() {
        this.em = JPAUtil.getEntityManager();
        this.subcategoryDAO = new SubcategoryDAO(em);
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
    }

    @AfterEach
    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Test
    void findActiveSubcategoriesAndPutInSequence__should_return_active_subcategories_in_sequence() {
        Subcategory java = new SubcategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("Cursos de java")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        Subcategory python = new SubcategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("Cursos de python")
                .withActive(false)
                .withSequence(1)
                .withCategory(category)
                .create();

        Subcategory kotlin = new SubcategoryBuilder()
                .withName("kotlin")
                .withCode("kotlin")
                .withDescription("Cursos de kotlin")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        em.persist(java);
        em.persist(python);
        em.persist(kotlin);

        List<Subcategory> activeSubcategoriesInSequence = subcategoryDAO.findActiveSubcategoriesAndPutInSequence();

        assertNotNull(activeSubcategoriesInSequence);
        assertEquals(2, activeSubcategoriesInSequence.size());
        assertEquals("java", activeSubcategoriesInSequence.get(0).getName());
        assertEquals("kotlin", activeSubcategoriesInSequence.get(1).getName());

    }

    @Test
    void findSubcategoriesWithoutDescription__should_return_subcategories_without_description() {

        Subcategory java = new SubcategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        Subcategory python = new SubcategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("Cursos de python")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        Subcategory kotlin = new SubcategoryBuilder()
                .withName("kotlin")
                .withCode("kotlin")
                .withDescription("")
                .withActive(true)
                .withSequence(1)
                .withCategory(category)
                .create();

        em.persist(java);
        em.persist(python);
        em.persist(kotlin);

        List<Subcategory> subcategoriesWithoutDescription = subcategoryDAO.findSubcategoriesWithoutDescription();

        assertNotNull(subcategoriesWithoutDescription);
        assertEquals(2, subcategoriesWithoutDescription.size());
        assertEquals("java", subcategoriesWithoutDescription.get(0).getName());
        assertEquals("kotlin", subcategoriesWithoutDescription.get(1).getName());

    }
}