<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Subcategorias">
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
                        <td id="activeStatus_${subcategory.id}">${subcategory.active}</td>
                        <td style="text-align: center">
                            <a  href="/admin/courses/${category.code}/${subcategory.code}">Cursos</a>
                        </td>
                        <td>
                            <a href="/admin/subcategories/${category.code}/${subcategory.code}">
                                <button type="submit">Editar</button>
                            </a>
                            <c:if test="${subcategory.active == 'Ativa'}">
                                <button class="button-disable" data-subcategory-id="${subcategory.id}">Desativar</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="../../../assets/js/jquery.js"></script>
    <script src="../../../assets/js/changeStatus.js"></script>
</templates:admin-template>