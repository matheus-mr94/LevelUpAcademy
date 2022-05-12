<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Novo Curso">
    <%--@elvariable id="courseCreateRequest" type="java"--%>
    <form:form modelAttribute="courseCreateRequest" action="/admin/courses" method="post" class="">
        <h2>Novo curso</h2>
        <div style="margin-top:30px;">
            <div class="mb-3 row form-group">
                <label for="name" class="form-label">Nome:</label>
                <input type="text" id="name" name="name" class="form-control"
                       placeholder="Digite aqui o nome do curso" value="${course.name}">
                <form:errors path="name" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="code"  class="form-label">Código: </label>
                <input type="text" id="code" name="code" class="form-control" value="${course.code}"
                       placeholder="por exemplo java (não use acentos, letras maísculas ou caracteres especiais)">
                <form:errors path="code" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="visible" name="visible"
                       placeholder="Mostra ou deixa de mostrar o curso na listagem dos alunos">
                <label  class="form-check-label" for="visible" class="form-label">
                    Curso visível ?
                </label>
            </div>

            <div class="mb-3 row form-group">
                <label for="estimatedTimeInHours" class="form-label">Tempo estimado para conclusão:</label>
                <input type="text" id="estimatedTimeInHours" name="estimatedTimeInHours" class="form-control"
                       value="${course.estimatedTimeInHours}"
                       placeholder="tempo estimado para conclusão do curso deve ser no mínimo 1 e no máximo 20">
                <form:errors path="estimatedTimeInHours" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="target" class="form-label">Público alvo:</label>
                <input type="text" id="target" name="target" class="form-control" value="${course.target}"
                       placeholder="público alvo, para quem seria interessante este curso">
                <form:errors path="target" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="instructor" class="form-label">Instrutor:</label>
                <input type="text" id="instructor" name="instructor" class="form-control" value="${course.instructor}"
                       placeholder="o nome do instrutor do curso">
                <form:errors path="instructor" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="syllabus" class="form-label">Ementa:</label><br>
                <textarea type="text" id="syllabus" name="syllabus" class="form-control" style="resize: none;" rows="8"
                          placeholder="uma descrição detalhada do conteúdo do curso ">${course.syllabus}</textarea>
                <form:errors path="syllabus" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <label for="developedSkills" class="form-label">Habilidades desenvolvidas:</label>
                <input type="text" id="developedSkills" name="developedSkills" class="form-control"
                       placeholder="habilidades que serão desenvolvidas durante a realização do curso"
                       value="${course.developedSkills}">
                <form:errors path="developedSkills" cssClass="alert-danger"/>
            </div>

            <div class="mb-3 row form-group">
                <select  name="subcategoryId" class="form-control">
                    <c:forEach items="${subcategories}" var="subcategory">
                        <option value="${subcategory.id}">${subcategory.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div style="margin-top: 10px;" class="mb-3 row form-group">
                <button type="submit" class="btn btn-info btn-lg">Enviar</button>
            </div>
        </div>
    </form:form>
</templates:admin-template>