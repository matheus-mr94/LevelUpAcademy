package br.com.levelupacademy.models.output;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class HtmlWriter {

    public static void outputWriter(List<Category> categories, List<Subcategory> subcategories, List<Course> courses) throws IOException {

        List<Subcategory> activeSubcategories = subcategories.stream()
                .filter(Subcategory::isActive).sorted(Comparator.comparing(Subcategory::getSequence)).toList();


        String cReader = "";
        for (Category category : categories) {
            cReader += String.format("""
                     <div style="background-color:%s;">
                        <div style="display:flex; align-items:center;">
                            <img src="%s" style="width:50px; height:50px"/>
                            <h2>%s</h2>
                        </div> 
                        <ul> 
                          <li>%s</li>
                          <li>Total de cursos: %d </li>
                          <li>Tempo estimado para conclusão dos cursos dessa categoria: %d </li>
                        </ul> 
                        <h4 style="margin-left:20px;">Subcategorias:</h4>
                        <dl style="margin-left:20px;">
                    
                    """, category.getHexCode(), category.getUrlImage(),
                    category.getName(), category.getDescription(),
                    numberOfCoursesInCategory(courses, category.getCode()),
                    totalEstimatedTimeInHours(courses, category.getCode()));

            for (Subcategory subcategory : filterSubcategoryByCategory(activeSubcategories, category.getCode())) {
                cReader += """
                            <dt>%s</dt>
                                <dd>Descrição: %s</dd>
                                <dd>Cursos: %s</dd>
                        """.formatted(subcategory.getName(), subcategory.getDescription(),
                            findCoursesNamesForSubcategory(courses,subcategory));

            }

            cReader += """
                    
                        </dl>
                    </div>
                    """;
        }


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teste.html"));

        String htmlFile = """
                <html>
                   <head>
                   </head>
                   <body style="background-color:#051933">
                       <h1 style="text-align:center;color:#fff;">LevelUp Academy</h1>
                      """ + cReader + """
                    </body>
                </html>
                """;


        bufferedWriter.write(htmlFile);
        bufferedWriter.close();
    }


       private static int numberOfCoursesInCategory(List<Course> courses, String categoryCode) {
          return (int) courses.stream()
                    .filter(course -> course.getCategoryCode().equals(categoryCode)).count();

       }

       private static int totalEstimatedTimeInHours(List<Course> courses, String categoryCode){
          return courses.stream()
                  .filter(course -> course.getCategoryCode().equals(categoryCode))
                  .mapToInt(Course::getEstimatedTimeInHours).sum();
       }

       private static List<Subcategory> filterSubcategoryByCategory(List<Subcategory> subcategoryList, String categoryCode) {
          List<Subcategory> subcategoryLists = subcategoryList.stream()
                  .filter(subcategory -> subcategory.getCategoryCode().equals(categoryCode)).toList();
          return subcategoryLists;
       }

       private static String findCoursesNamesForSubcategory(List<Course> courses, Subcategory subcategory) {
          return courses.stream()
                    .filter(course -> course.getSubcategory().equals(subcategory))
                    .map(Course::getName)
                    .collect(Collectors.joining(", "));
       }
}



