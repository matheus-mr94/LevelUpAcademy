package br.com.levelupacademy.servlet;

import br.com.levelupacademy.dao.CategoryDAO;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryDTO;
import br.com.levelupacademy.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atualizarCategoria")
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);

        em.getTransaction().begin();
        Category category = categoryDao.findById(id);
        em.close();

        request.setAttribute("category", category);

        RequestDispatcher rd = request.getRequestDispatcher("/updateCategory.jsp");
        rd.forward(request, resp);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String studyGuide = request.getParameter("studyGuide");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        Integer sequence = Integer.parseInt(request.getParameter("sequence"));
        String urlImage = request.getParameter("urlImage");
        String hexCode = request.getParameter("hexCode");

        CategoryDTO categoryDTO = new CategoryDTO(name, code, description,
                studyGuide, active, sequence, urlImage, hexCode );

        Category category = categoryDTO.toEntity();

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);
        em.getTransaction().begin();
        categoryDao.updateCategory(id, category);
        em.getTransaction().commit();
        em.close();

        resp.sendRedirect("/listaCategorias");

    }
}
