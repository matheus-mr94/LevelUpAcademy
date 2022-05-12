<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Admin dashboard">
    <div style="margin-top: 50px">
        <div class="panel panel-primary">
            <div class="panel-heading"><strong>Categorias</strong></div>
            <c:forEach items="${categories}" var="category">
                <div class="panel-body bg-info"><strong>${category.name}</strong> </div>
                <div class="panel-body">Número de cursos: ${category.countOfCourses} </div>
            </c:forEach>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">Instrutor com mais cursos: ${instructor.instructor}  </div>
            <div class="panel-body">Número de cursos: ${instructor.totalOfCourses}</div>
        </div>
    </div>
</templates:admin-template>