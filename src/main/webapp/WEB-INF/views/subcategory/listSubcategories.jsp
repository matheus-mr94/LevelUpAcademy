<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Subcategorias</title>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center";>LevelUp Academy</h1>

            <div class="col-lg-12">
                <div>
                    <c:catch var="category"/>
                    <h3>${category.name}</h3>
                    <h1>Subcategorias</h1>
                    <a href="/admin/subcategories/new">
                        <button class="btn btn-primary btn-lg"style="margin-top: 30px;">
                        Nova subcategoria
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
                        <c:forEach items="${subcategories}" var="subcategory">
                            <tr>
                                <td>${subcategory.name}</td>
                                <td>${subcategory.code}</td>
                                <td>${subcategory.getActive()}</td>
                                <td style="text-align: center">
                                    <a  href="/admin/courses/${subcategory.getCategoryCode()}/${subcategory.code}">Cursos</a>
                                </td>
                                <td>
                                    <a href="/admin/subcategories/${subcategory.getCategoryCode()}/${subcategory.code}">
                                        <button type="submit">Editar</button>
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
