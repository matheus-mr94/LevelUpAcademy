<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Atualizar categoria">
    <%--@elvariable id="categoryUpdateRequest" type="java"--%>
    <form:form modelAttribute="categoryUpdateRequest" action="/admin/categories/${category.code}" method="post" class="">
        <h1>Atualizar categoria</h1>
        <div style="margin-top:30px;">
            <div class="mb-3 row form-group">
                <input name="id" type="hidden" value="${category.id}">
                <label for="name" class="form-label">Nome:</label>
                <input type="text" id="name" name="name" class="form-control"
                       value="${category.name}">
                <form:errors path="name" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="code" class="form-label">Código: </label>
                <input type="text" id="code" name="code" class="form-control"
                       value="${category.code}">
                <form:errors path="code" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="active"
                       name="active"  ${category.isActive() ? 'checked' : ''}
                       placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos">
                <label  class="form-check-label" for="active" class="form-label">
                    Categoria Ativa ?
                </label>
            </div>

            <div class="mb-3 row form-group">
                <label for="sequence" class="form-label">Ordem da categoria:</label>
                <input type="text" id="sequence" name="sequence" class="form-control"
                       value="${category.sequence}">
                <form:errors path="sequence" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="studyGuide" class="form-label">Guia de estudos:</label><br>
                <textarea type="text" id="studyGuide" name="studyGuide" class="form-control"
                          style="resize: none;" rows="8">${category.studyGuide}</textarea>
                <form:errors path="studyGuide" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="urlImage" class="form-label">Caminho do ícone:</label>
                <input type="text" id="urlImage" name="urlImage" class="form-control"
                       value="${category.urlImage}">
                <form:errors path="urlImage" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="hexCode" class="form-label">Cor:</label>
                <input type="text" id="hexCode" name="hexCode" class="form-control"
                       value="${category.hexCode}">
                <form:errors path="hexCode" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="description" class="form-label">Descrição:</label>
                <input type="text" id="description" name="description" class="form-control"
                       value="${category.description}">
                <form:errors path="description" cssClass="alert-danger"/>
            </div>

            <div style="margin-top: 10px;" class="mb-3 row form-group">
                <button type="submit" class="btn btn-info btn-lg">Enviar</button>
            </div>
        </div>
    </form:form>
</templates:admin-template>