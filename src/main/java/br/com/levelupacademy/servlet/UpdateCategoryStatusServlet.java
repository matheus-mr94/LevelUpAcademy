package br.com.levelupacademy.servlet;

import br.com.levelupacademy.dao.CategoryDAO;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atualizarStatus")
public class UpdateCategoryStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(em);

        Long id = Long.parseLong(request.getParameter("id"));

        em.getTransaction().begin();
        Category category = categoryDAO.findById(id);
        category.toggleStatus();
        categoryDAO.update(category);
        em.getTransaction().commit();
        em.close();

        response.setStatus(204);

    }
}
