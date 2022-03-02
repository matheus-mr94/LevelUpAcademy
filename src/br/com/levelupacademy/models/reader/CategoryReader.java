package br.com.levelupacademy.models.reader;

import br.com.levelupacademy.models.category.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.levelupacademy.validators.Validations.getIntegerNumberOrZeroFrom;

public class  CategoryReader {

    public List<Category> readArchive(String filePath) throws IOException {


            List<Category> categories = new ArrayList<>();
            try (Scanner scan = new Scanner(new File(filePath), "UTF-8")) {
            scan.nextLine();

            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (!line.isEmpty()) {
                    String[] categoryData = line.split(",");
                    String name = categoryData[0];
                    String code = categoryData[1];
                    String sequence = categoryData[2];
                    int sequenceInSystem = getIntegerNumberOrZeroFrom(sequence);
                    String description = categoryData[3];
                    boolean active = categoryData[4].equals("ATIVA") ? true : false;
                    String urlImage = categoryData[5];
                    String hexCode = categoryData[6];

                    Category category = new Category(name, code, description, "", active, sequenceInSystem, urlImage, hexCode);
                    categories.add(category);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static List<Category> findActiveCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
    }

}
