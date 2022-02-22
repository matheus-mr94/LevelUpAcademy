package br.com.levelupacademy.models.output;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.reader.CategoryReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {
    public static void main(String[] args) throws IOException {

        CategoryReader categoryReader = new CategoryReader();
        List<Category> categories = categoryReader.readArchive("/home/matheus/Documentos/entradas/categoria.csv");
        String leitorCategoria = "";
        for (Category category: categories) {
             leitorCategoria += String.format("""
             <div style="background-color:%s;">
                <div style="display:flex"><img src="%s" style="width:50px; height:50px"/><h2>%s</h2></div> 
                <ul> 
                  <li>%s</li>
                  <li>Total de cursos: </li>
                  <li>Tempo estimado para conclusão dos cursos dessa categoria:</li>
                </ul> 
            </div>
            """,category.getHexCode(), category.getUrlImage(),category.getName(),category.getDescription());
        }


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teste.html"));

        String htmlFile = """
                 <html>
                    <head>
                    </head>
                    <body>
                        <h1 style="text-align:center;">LevelUp Academy</h1>
                       """+ leitorCategoria+"""
                    </body>
                </html>
                """;


        bufferedWriter.write(htmlFile);
        bufferedWriter.close();
    }
}




