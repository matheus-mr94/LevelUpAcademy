package br.com.levelupacademy.models.util;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class BDPopulator {

    public static void populing(TestEntityManager em) {
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

        em.persist(programacaoCategory);
        em.persist(devopsCategory);
        em.persist(dataScienceCategory);

        Subcategory java = new SubcategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withDescription("Cursos de java")
                .withActive(true)
                .withSequence(1)
                .withCategory(programacaoCategory)
                .create();

        Subcategory android = new SubcategoryBuilder()
                .withName("Android")
                .withCode("mobile")
                .withDescription("Cursos de desenvolvimento nativo")
                .withActive(true)
                .withSequence(2)
                .withCategory(programacaoCategory)
                .create();

        Subcategory devops = new SubcategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withDescription("Cursos de devops")
                .withActive(true)
                .withSequence(3)
                .withCategory(devopsCategory)
                .create();

        em.persist(java);
        em.persist(android);
        em.persist(devops);



        Course javaOO = new CourseBuilder()
                .withName("Java e POO")
                .withCode("java-e-poo")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(java)
                .create();

        Course javaExceptions = new CourseBuilder()
                .withName("Java e exceptions")
                .withCode("java-e-exceptions")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Nico")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(java)
                .create();

        Course mobile = new CourseBuilder()
                .withName("Desenvolvimento mobile")
                .withCode("mobile")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Manel")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(android)
                .create();

        Course terraform = new CourseBuilder()
                .withName("Terraform")
                .withCode("terraform")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Zeca")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(devops)
                .create();


        Course aws = new CourseBuilder()
                .withName("AWS")
                .withCode("aws")
                .withEstimatedTimeInHours(10)
                .withTarget("iniciantes em programação")
                .withVisible(true)
                .withInstructor("Joãozinho")
                .withSyllabus("Ementa")
                .withDevelopedSkills("POO")
                .withSubcategory(devops)
                .create();

        em.persist(javaOO);
        em.persist(javaExceptions);
        em.persist(mobile);
        em.persist(terraform);
        em.persist(aws);
    }
}
