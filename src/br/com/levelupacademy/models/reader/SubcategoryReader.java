package br.com.levelupacademy.models.reader;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.levelupacademy.validators.Validations.verifyIntegerNumber;

public class SubcategoryReader {

    public List<Subcategory> readArchive(String filePath, List<Category> categories) throws IOException {
        try {

            List<Subcategory> subCategories = new ArrayList<>();
            File archive = new File(filePath);
            Scanner scan = new Scanner(archive, "UTF-8");
            scan.nextLine();

            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (!line.isEmpty()) {
                    String[] subcategoryData = line.split(",");
                    String name = subcategoryData[0];
                    String code = subcategoryData[1];
                    String sequence = subcategoryData[2];
                    int sequenceInSystem = verifyIntegerNumber(sequence);
                    String description = subcategoryData[3];
                    boolean active = subcategoryData[4].equals("ATIVA") ? true : false;
                    String categoryCode = subcategoryData[5];

                    Category category = null;
                    for (Category c : categories) {
                        if(categoryCode.equals(c.getCode())) {
                            category = c;
                        }
                    }

                    try {
                        Subcategory subcategory = new Subcategory(name, code, description, "", active, sequenceInSystem, category);
                        subCategories.add(subcategory);
                    } catch (NullPointerException e) {
                        System.out.println("The file wasn't read correctly, some field is empty");
                    }
                }
            }
                scan.close();

            for (Subcategory subcategory : subCategories) {
                    System.out.println(subcategory);
                }
            return subCategories;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}