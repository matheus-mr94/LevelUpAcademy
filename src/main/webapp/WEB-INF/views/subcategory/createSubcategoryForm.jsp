<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Nova categoria</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <%--@elvariable id="subcategoryCreateRequest" type="java"--%>
            <form:form modelAttribute="subcategoryCreateRequest" action="/admin/subcategories" method="post" class="">
                <h2>Nova subcategoria</h2>
                <div style="margin-top:30px;">
                    <div class="mb-3 row form-group">
                        <label for="name" class="form-label">Nome:</label>
                        <input type="text" id="name" name="name" class="form-control"
                               placeholder="Digite aqui o nome da subcategoria" value="${subcategory.name}">
                        <form:errors path="name" cssClass="alert-danger"/>
                    </div>

                    <div class="mb-3 row form-group">
                        <label for="code"  class="form-label">Código: </label>
                        <input type="text" id="code" name="code" class="form-control" value="${subcategory.code}"
                               placeholder="por exemplo java (não use acentos, letras maísculas ou caracteres especiais)">
                        <form:errors path="code" cssClass="alert-danger"/>
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="active" name="active"
                               placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos">
                        <label  class="form-check-label" for="active" class="form-label">
                            Subcategoria Ativa ?
                        </label>
                    </div>

                    <div class="mb-3 row form-group">
                        <label for="sequence" class="form-label">Ordem da categoria:</label>
                        <input type="text" id="sequence" name="sequence" class="form-control" value="${subcategory.sequence}"
                               placeholder="por exemplo categoria de ordem 1 aparece antes da categoria de ordem 2">
                        <form:errors path="sequence" cssClass="alert-danger"/>
                    </div>

                    <div class="mb-3 row form-group">
                        <label for="studyGuide" class="form-label">Guia de estudos:</label><br>
                        <textarea type="text" id="studyGuide" name="studyGuide" class="form-control" style="resize: none;" rows="8"
                                  placeholder="Um texto apontando para formações para ajudar pessoas perdidas">${subcategory.studyGuide}</textarea>
                        <form:errors path="studyGuide" cssClass="alert-danger"/>
                    </div>

                    <div class="mb-3 row form-group">
                        <label for="description" class="form-label">Descrição:</label>
                        <input type="text" id="description" name="description" class="form-control"
                               placeholder="por exemplo: desenvolvimento java" value="${subcategory.description}">
                        <form:errors path="description" cssClass="alert-danger"/>
                    </div>

                    <div class="mb-3 row form-group">
                        <select  name="categoryId" class="form-control">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div style="margin-top: 10px;" class="mb-3 row form-group">
                        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>
