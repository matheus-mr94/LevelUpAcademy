package br.com.levelupacademy.models.output;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class HtmlReportWriter {

    public static void writeReport(List<Subcategory> activeSubcategories,
                            List<Subcategory> subcategoriesWithoutDescription,
                            List<Course> publicCourses, List<Category> activeCategories) {


        String cReader = "";

        cReader += """
                    <h3>Categorias Ativas</h3>
                """;

        for(Category category : activeCategories) {

            cReader += """
                         <div>
                            <ul>
                                <li> ID: %d </li>
                                <li> Nome da categoria: %s </li>
                                <li> Código: %s </li>
                                <li> Descrição: %s </li>
                                <li> Guia de estudos: %s </li>
                                <li> Imagem: %s </li>
                                <li> Cor: %s </li>
                            </ul>
                         </div>
                    """.formatted(category.getId(), category.getName(), category.getCode(), category.getDescription(),
                        category.getStudyGuide(), category.getUrlImage(), category.getHexCode());
        }

        cReader += """
                     <h3>Subcategorias Ativas</h3>
                """;

        for(Subcategory subcategory : activeSubcategories) {

            cReader += """
                     
                         <div>
                            <ul>
                                <li> ID: %d </li>
                                <li> Nome da subcategoria: %s </li>
                                <li> Código: %s </li>
                                <li> Descrição: %s </li>
                                <li> Guia de estudos: %s </li>
                                <li> Categoria: %s </li>
                            </ul>
                         </div>
                    """.formatted(subcategory.getId(), subcategory.getName(), subcategory.getCode(), subcategory.getDescription(),
                    subcategory.getStudyGuide(), subcategory.getCategoryName());
        }

        cReader += """
                     <h3>Subcategorias sem descrição: </h3>
                """;

        for(Subcategory subcategory : subcategoriesWithoutDescription) {

            cReader += """
                     
                         <div>
                            <ul>
                                <li> ID: %d </li>
                                <li> Nome da subcategoria: %s </li>
                                <li> Código: %s </li>
                                <li> Descrição: %s </li>
                                <li> Guia de estudos: %s </li>
                                <li> Categoria: %s </li>
                            </ul>
                         </div>
                    """.formatted(subcategory.getId(), subcategory.getName(), subcategory.getCode(), subcategory.getDescription(),
                    subcategory.getStudyGuide(), subcategory.getCategoryName());
        }

        cReader += """
                     <h3>Cursos públicos: </h3>
                """;

        for(Course course : publicCourses) {

            cReader += """
                     
                         <div>
                            <ul>
                                <li> ID: %d </li>
                                <li> Nome do curso: %s </li>
                                <li> Código: %s </li>
                                <li> Tempo estimado de conclusão: %d</li>
                                <li> Público alvo: %s </li>
                                <li> Instrutor: %s </li>
                                <li> Ementa: %s </li>
                                <li> Habilidades desenvolvidas: %s </li>
                                <li> Subcategoria: %s </li>
                            </ul>
                         </div>
                    """.formatted(course.getId(), course.getName(), course.getCode(), course.getEstimatedTimeInHours(),
                    course.getTarget(), course.getInstructor(), course.getSyllabus(), course.getDevelopedSkills(),
                    course.getSubcategoryName());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("report.html"))) {

            String htmlFile = """
                    <html>
                       <head>
                           <meta charset="UTF-8">
                       </head>
                       <body>
                           <h1>LevelUp Academy</h1>
                          """ + cReader + """
                       </body>
                    </html>
                    """;

            bufferedWriter.write(htmlFile);
            System.out.println("Report created!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
