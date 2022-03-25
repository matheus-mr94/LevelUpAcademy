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
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/listaCategorias")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDao = new CategoryDAO(em);
        em.getTransaction().begin();
        List<Category> categories = categoryDao.findAll();
        em.close();
        String categoryList = "";

        for(Category category : categories) {

            categoryList +="""
                                <div>
                                    <ul>
                                        <li> ID da categoria: %d </li>
                                        <li> Nome da categoria : %s </li>
                                        <li> Descrição: %S </li>
                                        <li> Guia de estudos: %S </li>
                                        <li> Url da imagem: %s </li>
                                        <li style="background-color: %s";> Cor Hexadecimal </li>
                                    </ul>
                                </div>
                    """.formatted(category.getId(), category.getName(), category.getDescription(),
                    category.getStudyGuide(), category.getUrlImage(), category.getHexCode());
        }

        String html = """
                 <html>
                       <head>
                            <title>Categories</title>
                       </head>
                       <body>
                            <h1>LevelUp Academy</h1>
                          """ + categoryList + """
                       </body>
                 </html>
                                    
                """;

        PrintWriter out = resp.getWriter();
        out.println(html);
        out.close();
    }
}

