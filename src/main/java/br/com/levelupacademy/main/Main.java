package br.com.levelupacademy.main;

import br.com.levelupacademy.models.alternative.Alternative;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.explanation.Explanation;
import br.com.levelupacademy.models.output.SqlWriter;
import br.com.levelupacademy.models.question.Question;
import br.com.levelupacademy.models.reader.CategoryReader;
import br.com.levelupacademy.models.reader.CourseReader;
import br.com.levelupacademy.models.reader.SubcategoryReader;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.video.Video;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            CategoryReader categoryReader = new CategoryReader();
            List<Category> categories = categoryReader.readArchive("/home/matheus/Documentos/entradas/categoria.csv");
            categories.forEach(c -> System.out.println(c));
            System.out.println("==================");

            SubcategoryReader subcategoryReader = new SubcategoryReader();
            List<Subcategory> subcategories = subcategoryReader.readArchive("/home/matheus/Documentos/entradas/subcategoria.csv", categories);
            subcategories.forEach(s -> System.out.println(s));
            System.out.println("==================");

            CourseReader courseReader = new CourseReader();
            List<Course> courses = courseReader.readArchive("/home/matheus/Documentos/entradas/curso.csv", subcategories);
            courses.forEach(c -> System.out.println(c));
            System.out.println("============================");

            SqlWriter sw = new SqlWriter();
            sw.queryWriter(categories, subcategories, courses);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}