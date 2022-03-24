package br.com.levelupacademy.main;

import br.com.levelupacademy.dao.CategoryDAO;
import br.com.levelupacademy.dao.CourseDAO;
import br.com.levelupacademy.dao.SubcategoryDAO;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.levelupacademy.models.output.HtmlReportWriter.writeReport;
import static br.com.levelupacademy.utils.JPAUtil.getEntityManager;

public class ReportGenerator {

    public static void main(String[] args) {
        EntityManager em = getEntityManager();

        SubcategoryDAO subcategoryDAO = new SubcategoryDAO(em);
        CourseDAO dao = new CourseDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);

        List<Course> publicCourses = dao.findPublicCourses();
        List<Subcategory> subcategoriesActive = subcategoryDAO.findActiveSubcategoriesOrderedBySequence();
        List<Category> categoriesActive = categoryDAO.findActiveCategoriesOrderedBySequence();
        List<Subcategory> subcategoriesWithoutDescription = subcategoryDAO.findSubcategoriesWithoutDescription();

        em.close();
        writeReport(subcategoriesActive, subcategoriesWithoutDescription, publicCourses, categoriesActive);
    }
}
