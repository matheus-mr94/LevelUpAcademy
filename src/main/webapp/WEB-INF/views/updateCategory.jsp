<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Atualizar categoria</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <form action="/admin/categories/${category.code}" method="post" class="">
                <h2>Atualizar categoria</h2>
                <div style="margin-top:30px;">
                    <div class="mb-3 row">
                        <input name="id" type="hidden" value="${category.id}">
                        <label for="name" class="form-label">Nome:</label>
                        <input type="text" id="name" name="name" class="form-control"
                               value="${category.name}">
                    </div>

                    <div class="mb-3 row">
                        <label for="code" style="margin-left: 5px"; class="form-label">Código: </label>
                        <input type="text" id="code" name="code" class="form-control"
                               value="${category.code}">
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="active"
                               name="active"  ${category.isActive() ? 'checked' : ''}
                               placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos">
                        <label  style="margin-top: 5px;" class="form-check-label" for="active" class="form-label">
                            Categoria Ativa ?
                        </label>
                    </div>

                    <div class="mb-3 row">
                        <label for="sequence" class="form-label">Ordem da categoria:</label>
                        <input type="text" id="sequence" name="sequence" class="form-control"
                               value="${category.sequence}">
                    </div>

                    <div class="mb-3 row">
                        <label for="studyGuide" class="form-label">Guia de estudos:</label><br>
                        <textarea type="text" id="studyGuide" name="studyGuide" class="form-control"
                                  style="resize: none; height: 250px;" value="${category.studyGuide}">
                        </textarea>
                    </div>

                    <div class="mb-3 row">
                        <label for="urlImage" class="form-label">Caminho do ícone:</label>
                        <input type="text" id="urlImage" name="urlImage" class="form-control"
                               value="${category.urlImage}">
                    </div>

                    <div class="mb-3 row">
                        <label for="hexCode" class="form-label">Cor:</label>
                        <input type="text" id="hexCode" name="hexCode" class="form-control"
                               value="${category.hexCode}">
                    </div>

                    <div class="mb-3 row">
                        <label for="description" class="form-label">Descrição:</label>
                        <input type="text" id="description" name="description" class="form-control"
                               value="${category.description}">
                    </div>

                    <div style="margin-top: 10px;" class="mb-3 row">
                        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
                    </div>
                </div>
            </form>
        </div>
<%--            <form action="/admin/categories" method="post">--%>
<%--                <h2>Atualizar categoria</h2>--%>
<%--                <label for="name">ID: ${category.id}</label><br>--%>
<%--                <input name="id" type="hidden" value="${category.id}">--%>

<%--                <div style="display:flex";>--%>
<%--                    <label for="name">Nome:</label><br>--%>
<%--                    <input type="text" id="name" name="name" value="${category.name}">--%>

<%--                    <label for="code" style="margin-left: 5px";>Código: </label><br>--%>
<%--                    <input type="text" id="code" name="code" value="${category.code}">--%>
<%--                </div><br>--%>

<%--                <label for="description">Descrição:</label><br>--%>
<%--                <input type="text" id="description" name="description" value="${category.description}"><br>--%>

<%--                <label for="studyGuide">Guia de estudos:</label><br>--%>
<%--                <input type="text" id="studyGuide" name="studyGuide" value="${category.studyGuide}"><br><br>--%>

<%--                <div style="display: flex";>--%>
<%--                    <label for="active">Ativa:</label>--%>
<%--                    <select style="margin-left: 5px" id="active" name="active">--%>
<%--                        <option value="true" ${category.active == true ? "selected" : ""}>Ativa</option>--%>
<%--                        <option value ="false" ${category.active == false ? "selected" : ""}>Inativa</option>--%>
<%--                    </select><br>--%>
<%--                </div><br><br>--%>

<%--                <label for="sequence">Ordem no sistema:</label><br>--%>
<%--                <input type="text" id="sequence" name="sequence" value="${category.sequence}"><br><br>--%>

<%--                <label for="urlImage">Url da imagem:</label><br>--%>
<%--                <input type="text" id="urlImage" name="urlImage" value="${category.urlImage}"><br><br>--%>

<%--                <label for="hexCode">Cor em hexadecimal:</label><br>--%>
<%--                <input type="text" id="hexCode" name="hexCode" value="${category.hexCode}"><br><br>--%>

<%--                <input type="submit" value="Atualizar">--%>
<%--            </form>--%>

    </body>
</html>
