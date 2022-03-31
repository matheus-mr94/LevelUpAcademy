<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/criarCategoria" var="createCategory"/>
<html>
<head>
    <title>Nova categoria</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<form action="${createCategory}" method="post">
    <h2>Inserir nova categoria</h2>
    <div style="display:flex";>
        <label for="name">Nome:</label><br>
        <input type="text" id="name" name="name"><br>

        <label for="code" style="margin-left: 5px";>Código: </label><br>
        <input type="text" id="code" name="code"><br>
    </div><br>

    <label for="description">Descrição:</label><br>
    <textarea type="text" id="description" name="description"></textarea><br><br>

    <label for="studyGuide">Guia de estudos:</label><br>
    <textarea type="text" id="studyGuide" name="studyGuide"></textarea><br><br>

    <div style="display: flex";>
        <label for="active">Ativa:</label><br>
        <select style="margin-left: 5px" id="active" name="active">
            <option value="true">Ativa</option>
            <option value ="false">Inativa</option>
        </select>
    </div><br><br>

    <label for="sequence">Ordem no sistema:</label><br>
    <input type="text" id="sequence" name="sequence"><br><br>

    <label for="urlImage">Url da imagem:</label><br>
    <input type="text" id="urlImage" name="urlImage"><br><br>

    <label for="hexCode">Cor em hexadecimal:</label><br>
    <input type="text" id="hexCode" name="hexCode"><br><br>

    <input type="submit" value="Cadastrar">
</form>

</body>
</html>
