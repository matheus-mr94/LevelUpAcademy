<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="br.com.levelupacademy.models.category.Category, java.util.List" %>
<html>
    <head>
        <title>Categories</title>
    </head>
    <body>
        <%
            List<Category> categoryList = (List<Category>) request.getAttribute("categories");
            for(Category c : categoryList) {
        %>

        <div>
            <ul>
                <li> ID da categoria:<%= c.getId()%>  </li>
                <li> Nome da categoria :<%= c.getName()%>   </li>
                <li> Descrição:<%= c.getDescription()%>  </li>
                <li> Guia de estudos: <%= c.getStudyGuide()%>  </li>
                <li> Url da imagem: <%= c.getUrlImage()%>  </li>
                <li style="background-color:<%= c.getHexCode()%>";> Cor Hexadecimal </li>
            </ul>
        </div>
    <% }%>
    </body>
</html>
