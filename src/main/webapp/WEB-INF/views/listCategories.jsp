<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Categorias</title>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center";>LevelUp Academy</h1>

            <div class="col-lg-12">
                <div>
                    <h2>Categorias</h2>
                    <a href="/admin/categories/new">
                        <button class="btn btn-primary"style="margin-top: 30px;">
                        Nova categoria
                        </button>
                    </a>
                </div>

                <div style="margin-top: 25px;">
                    <table class="table table-bordered">
                        <thead>
                            <th>Nome</th>
                            <th>Código</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </thead>
                        <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td>${category.name}</td>
                                <td>${category.code}</td>
                                <td>${category.active}</td>
                                <td style="text-align: center">
                                    <a  href="/admin/subcategories/${category.code}">Subcategorias</a>
                                </td>
                                <td>
                                    <a href="/admin/categories/${category.code}">
                                        <button type="submit" href="">Editar</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
