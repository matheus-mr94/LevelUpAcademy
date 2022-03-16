package br.com.levelupacademy.main;

import br.com.levelupacademy.factory.ConnectionFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportWriter {

    private Connection connection;

    public ReportWriter(Connection connection) {
        this.connection = connection;
    }

    public  void report() {
        String sql = "SELECT c.`id`, c.`name`, c.estimated_time_in_hours, c.subcategory_id, s.`name` " +
                "FROM Course c\n" +
                "INNER JOIN Subcategory s  ON c.subcategory_id = s.`id` WHERE `visible`;";

        String cReader = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String course = resultSet.getString(2);
                    Integer estimatedTimeInHours = resultSet.getInt(3);
                    Long subcategoryId = resultSet.getLong(4);
                    String subcategory = resultSet.getString(5);

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
                            """.formatted(id, course, estimatedTimeInHours, subcategoryId, subcategory);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
