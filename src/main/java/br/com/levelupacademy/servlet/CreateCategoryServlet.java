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

@WebServlet("/criarCategoria")
public class CreateCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("createCategory.jsp");
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String studyGuide = request.getParameter("studyGuide");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        Integer sequence = Integer.parseInt(request.getParameter("sequence"));
        String urlImage = request.getParameter("urlImage");
        String hexCode = request.getParameter("hexCode");

        CategoryDTO categoryDTO = new CategoryDTO(name, code, description, studyGuide, active, sequence,
                urlImage, hexCode);
        
        Category category = categoryDTO.toEntity();

        em.getTransaction().begin();
        categoryDao.create(category);
        em.getTransaction().commit();
        System.out.println("Category created with success!");
        em.close();

        request.setAttribute("category", "category");
        response.sendRedirect("listaCategorias");
    }
}