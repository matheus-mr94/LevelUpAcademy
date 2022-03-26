package br.com.levelupacademy.servlet;

import br.com.levelupacademy.dao.CategoryDAO;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);
        em.getTransaction().begin();
        List<Category> categories = categoryDao.findAll();
        em.close();

        request.setAttribute("categories", categories);
        RequestDispatcher rd = request.getRequestDispatcher("/listCategories.jsp");
        rd.forward(request, response);

    }
}

