<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="br.com.levelupacademy.models.category.Category, java.util.List" %>
<html>
    <head>
        <title>Categorias</title>
    </head>
    <body>
         <c:forEach items="${categories}" var="category">
            <div>
                <ul>
                    <li> ID da categoria:${category.id}
                        <button><a href="/categoriaSelecionada?id=${category.id}" style="text-decoration: none";>Editar</a></button>
                        <button id="button-${category.id}" onclick="changeStatus(${category.id}, this)" style="margin-left: 5px";>
                            Alternar status
                        </button>
                    </li>
                    <li> Nome da categoria: ${category.name}  </li>
                    <li id="active-${category.id}">Ativa: ${category.active}</li>
                    <li> Descrição: ${category.description}  </li>
                    <li> Guia de estudos: ${category.studyGuide} </li>
                    <li> Url da imagem: ${category.urlImage} </li>
                    <li style="background-color:${category.hexCode}";> Cor Hexadecimal </li>
                </ul>
            </div>
        </c:forEach>

        <script src="js/changeStatus.js"></script>

    </body>
</html>
