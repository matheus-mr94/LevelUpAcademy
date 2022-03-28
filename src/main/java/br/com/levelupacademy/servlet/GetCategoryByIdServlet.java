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

@WebServlet("/categoriaSelecionada")
public class GetCategoryByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String paramId = request.getParameter("id");
        Long id = Long.parseLong(paramId);

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);

        em.getTransaction().begin();
        Category category = categoryDao.findById(id);
        em.close();

        request.setAttribute("category", category);

        RequestDispatcher rd = request.getRequestDispatcher("/updateCategory.jsp");
        rd.forward(request, resp);

    }
}
