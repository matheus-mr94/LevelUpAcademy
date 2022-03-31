<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/atualizarCategoria" var="updateCategory"/>
<html>
<head>
    <title>Atualizar categoria</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<form action="${updateCategory}" method="post">
    <h2>Atualizar categoria</h2>
    <label for="name">ID: ${category.id}</label><br>
    <input name="id" type="hidden" value="${category.id}"><br>

    <div style="display:flex";>
        <label for="name">Nome:</label><br>
        <input type="text" id="name" name="name" value="${category.name}">

        <label for="code" style="margin-left: 5px";>Código: </label><br>
        <input type="text" id="code" name="code" value="${category.code}">
    </div><br>

    <label for="description">Descrição:</label><br>
    <input type="text" id="description" name="description" value="${category.description}"><br>

    <label for="studyGuide">Guia de estudos:</label><br>
    <input type="text" id="studyGuide" name="studyGuide" value="${category.studyGuide}"><br><br>

    <div style="display: flex";>
        <label for="active">Ativa:</label>
        <select style="margin-left: 5px" id="active" name="active">
            <option value="true" ${category.active == true ? "selected" : ""}>Ativa</option>
            <option value ="false" ${category.active == false ? "selected" : ""}>Inativa</option>
        </select><br>
    </div><br><br>

    <label for="sequence">Ordem no sistema:</label><br>
    <input type="text" id="sequence" name="sequence" value="${category.sequence}"><br><br>

    <label for="urlImage">Url da imagem:</label><br>
    <input type="text" id="urlImage" name="urlImage" value="${category.urlImage}"><br><br>

    <label for="hexCode">Cor em hexadecimal:</label><br>
    <input type="text" id="hexCode" name="hexCode" value="${category.hexCode}"><br><br>

    <input type="submit" value="Atualizar">
</form>

</body>
</html>
