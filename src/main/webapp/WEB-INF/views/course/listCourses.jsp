<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Courses</title>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center";>LevelUp Academy</h1>

            <div class="col-lg-12">
                <div>
                    <c:catch var="subcategory"/>
                    <h3>${subcategory.name}</h3>
                    <h1>Cursos</h1>
                    <a href="/admin/courses/new">
                        <button class="btn btn-primary btn-lg"style="margin-top: 30px;">
                        Novo curso
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
                        </thead>
                        <tbody>
                        <c:forEach items="${courses.content}" var="course">
                            <tr>
                                <td>${course.name}</td>
                                <td>${course.code}</td>
                                <td>${course.active}</td>
                                <td>
                                    <a href="/admin/courses/${subcategory.categoryCode}/${subcategory.code}/${course.code}">
                                        <button type="submit">Editar</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <nav >
                        <ul class="pagination">
                            <li class="${courses.first == true ? 'disabled': ''}" >
                                <a href="/admin/courses/${subcategory.categoryCode}/${subcategory.code}?page=${courses.number > 1 ? courses.number-1 : 1}">Anterior</a>
                            </li>
                            <c:forEach begin="1" end="${courses.totalPages}" var="page">
                                <li class="${courses.number+1 == page ? 'active' : ''}">
                                    <a href="/admin/courses/${subcategory.categoryCode}/${subcategory.code}?page=${page}">${page}</a>
                                </li>
                            </c:forEach>
                            <li class="${courses.last == true ? 'disabled': ''}">
                                <a href="/admin/courses/${subcategory.categoryCode}/${subcategory.code}?page=${courses.number < 1 ? 2 : courses.number+1}">Próximo</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
