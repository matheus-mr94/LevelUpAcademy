package br.com.levelupacademy.models.output;

import br.com.levelupacademy.dao.CourseDAO;
import br.com.levelupacademy.dto.CourseDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportWriter{

    private CourseDAO courseDAO;

    public void writeReport() {

        String cReader = "";
        List<CourseDTO> report;

        try{
            report = courseDAO.report();

        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }


        for(CourseDTO courseDTO : report) {
            Long id = courseDTO.getId();
            String courseName = courseDTO.getCourseName();
            int estimatedTimeInHours = courseDTO.getEstimatedTimeInHours();
            Long subcategoryId = courseDTO.getSubcategoryId();
            String subcategoryName = courseDTO.getSubcategoryName();

            cReader += """
                            <div>
                                <ul>
                                    <li> ID do curso: %d </li>
                                    <li> Nome do curso: %s </li>
                                    <li> Tempo de conclusão estimado: %d </li>
                                    <li> ID da subcategoria: %d </li>
                                    <li> Nome da subcategoria: %s </li>
                                </ul>
                            </div>
                    """.formatted(id, courseName, estimatedTimeInHours, subcategoryId, subcategoryName);
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
