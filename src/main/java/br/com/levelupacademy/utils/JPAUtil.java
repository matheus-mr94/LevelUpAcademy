package br.com.levelupacademy.utils;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPAUtil {
    public static void main(String[] args) {

    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("levelUpAcademy");

    EntityManager em = factory.createEntityManager();

    List<Category> categories = buscarTodos(em);
    categories.stream().forEach(c -> System.out.println(c));

    }
    public static List<Category> buscarTodos(EntityManager em) {
        String jpql = "SELECT c FROM Category c";
        return em.createQuery(jpql, Category.class).getResultList();
    }
}


