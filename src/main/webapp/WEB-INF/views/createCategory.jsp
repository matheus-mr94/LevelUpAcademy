<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Nova categoria</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <form action="/admin/categories" method="post" class="">
                <h2>Nova categoria</h2>
                <div style="margin-top:30px;">
                    <div class="mb-3 row">
                        <label for="name" class="form-label">Nome:</label>
                        <input type="text" id="name" name="name" class="form-control"
                               placeholder="Digite aqui o nome da categoria">
                    </div>

                    <div class="mb-3 row">
                        <label for="code" style="margin-left: 5px"; class="form-label">Código: </label>
                        <input type="text" id="code" name="code" class="form-control"
                               placeholder="por exemplo programacao (não use acentos, letras maísculas ou caracteres especiais)">
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="active" name="active"
                               placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos">
                        <label  style="margin-top: 5px;" class="form-check-label" for="active" class="form-label">
                            Categoria Ativa ?
                        </label>
                    </div>

                    <div class="mb-3 row">
                        <label for="sequence" class="form-label">Ordem da categoria:</label>
                        <input type="text" id="sequence" name="sequence" class="form-control"
                               placeholder="por exemplo categoria de ordem 1 aparece antes da categoria de ordem 2">
                    </div>

                    <div class="mb-3 row">
                        <label for="studyGuide" class="form-label">Guia de estudos:</label><br>
                        <textarea type="text" id="studyGuide" name="studyGuide" class="form-control"
                                  style="resize: none; height: 250px;"
                                  placeholder="Um texto apontando para formações para ajudar pessoas perdidas">
                        </textarea>
                    </div>

                    <div class="mb-3 row">
                        <label for="urlImage" class="form-label">Caminho do ícone:</label>
                        <input type="text" id="urlImage" name="urlImage" class="form-control"
                               placeholder="a url da onde o arquivo está">
                    </div>

                    <div class="mb-3 row">
                        <label for="hexCode" class="form-label">Cor:</label>
                        <input type="text" id="hexCode" name="hexCode" class="form-control"
                               placeholder="por exemplo: #fcc14a">
                    </div>

                    <div class="mb-3 row">
                        <label for="description" class="form-label">Descrição:</label>
                        <input type="text" id="description" name="description" class="form-control"
                               placeholder="por exemplo: desenvolvimento java">
                    </div>

                    <div style="margin-top: 10px;" class="mb-3 row">
                        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
