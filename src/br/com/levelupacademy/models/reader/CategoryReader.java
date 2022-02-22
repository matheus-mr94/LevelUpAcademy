package br.com.levelupacademy.models.reader;

import br.com.levelupacademy.models.category.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.levelupacademy.validators.Validations.verifyIntegerNumber;

public class  CategoryReader {

    public List<Category> readArchive(String filePath) throws IOException {
        try {

            List<Category> categories = new ArrayList<>();
            File archive = new File(filePath);
            Scanner scan = new Scanner(archive, "UTF-8");
            scan.nextLine();

            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (!line.isEmpty()) {
                    String[] categoryData = line.split(",");
                    String name = categoryData[0];
                    String code = categoryData[1];
                    String sequence = categoryData[2];
                    int sequenceInSystem = verifyIntegerNumber(sequence);
                    String description = categoryData[3];
                    boolean active = categoryData[4].equals("ATIVA") ? true : false;
                    String urlImage = categoryData[5];
                    String hexCode = categoryData[6];

                    Category category = new Category(name, code, description, "", active, sequenceInSystem, urlImage, hexCode);
                    categories.add(category);

                }
            }
            scan.close();
            for (Category category : categories) {
                System.out.println(category);
            }

            return categories;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");

        }
    }
}