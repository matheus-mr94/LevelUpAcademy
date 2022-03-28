<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/criarCategoria" var="createCategory"/>
<html>
<head>
    <title>Nova categoria</title>
    <meta charset="UTF-8"/>
</head>
<body>

<form action="${createCategory}" method="post">
    <h2>Inserir nova categoria</h2>
    <div style="display:flex";>
        <label for="name">Nome:</label><br>
        <input type="text" id="name" name="name"><br>

        <label for="code" style="margin-left: 5px";>Código: </label><br>
        <input type="text" id="code" name="code"><br>
    </div>

    <label for="description">Descrição:</label><br>
    <input type="text" id="description" name="description"><br>

    <label for="studyGuide">Guia de estudos:</label><br>
    <input type="text" id="studyGuide" name="studyGuide"><br>

    <label for="active">Ativa:</label><br>
    <input type="text" id="active" name="active"><br>

    <label for="sequence">Ordem no sistema:</label><br>
    <input type="text" id="sequence" name="sequence"><br>

    <label for="urlImage">Url da imagem:</label><br>
    <input type="text" id="urlImage" name="urlImage"><br>

    <label for="hexCode">Cor em hexadecimal:</label><br>
    <input type="text" id="hexCode" name="hexCode"><br><br>

    <input type="submit" value="Cadastrar">
</form>

</body>
</html>
