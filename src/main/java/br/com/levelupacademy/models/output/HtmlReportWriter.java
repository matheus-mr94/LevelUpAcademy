package br.com.levelupacademy.models.output;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlReportWriter {

    public void writeReport(List<Subcategory> activeSubcategories,
                            List<Subcategory> subcategoriesWithoutDescription,
                            List<Course> publicCourses, List<Category> activeCategories) {


        String cReader = "";


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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
