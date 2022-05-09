<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Categorias">
    <h1 style="text-align: center";>LevelUp Academy</h1>

    <div class="col-lg-12">
        <div>
            <h1>Categorias</h1>
            <a href="/admin/categories/new">
                <button class="btn btn-primary btn-lg"style="margin-top: 30px;">
                Nova categoria
                </button>
            </a>
        </div>

        <div style="margin-top: 25px; display: flex">
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
                        <td id="status_${category.id}" class="status" >${category.active}</td>
                        <td style="text-align: center">
                            <a  href="/admin/subcategories/${category.code}">Subcategorias</a>
                        </td>
                        <td>
                            <a href="/admin/categories/${category.code}">
                                <button type="submit">Editar</button>
                            </a>
                            <c:if test="${category.active == 'Ativa'}">
                                <button class="btn-disable" data-category-id="${category.id}">Desativar</button>
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