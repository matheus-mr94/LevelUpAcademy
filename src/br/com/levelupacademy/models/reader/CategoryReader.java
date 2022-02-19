package br.com.levelupacademy.models.reader;

import br.com.levelupacademy.models.category.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryReader {

    public void readArchive(String filePath) throws IOException {
        try {

            List<Category> categories = new ArrayList<>();
            File archive = new File(filePath);
            Scanner scan = new Scanner(archive, "UTF-8");

            boolean isFirst = true;
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (isFirst) {
                    line = scan.nextLine();
                }
                if (!line.isEmpty()) {
                    String[] categoryData = line.split(",");
                    String name = categoryData[0];
                    String code = categoryData[1];
                    String orderInSystem = categoryData[2];
                    int order = Integer.parseInt(orderInSystem);
                    String description = categoryData[3];
                    String status = categoryData[4];
                    boolean active;
                    if (status.equals("ATIVA")) {
                        active = true;
                    } else {
                        active = false;
                    }
                    String urlImage = categoryData[5];
                    String hexCode = categoryData[6];

                    Category category = new Category(name, code, description, "", active, order, urlImage, hexCode);
                    categories.add(category);
                    isFirst = false;
                }
            }
            scan.close();
            for ( Category category: categories) {
                System.out.println(category);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
