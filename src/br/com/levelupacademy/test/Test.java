package br.com.levelupacademy.test;

import br.com.levelupacademy.models.alternative.Alternative;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.explanation.Explanation;
import br.com.levelupacademy.models.output.HtmlWriter;
import br.com.levelupacademy.models.question.Question;
import br.com.levelupacademy.models.reader.CategoryReader;
import br.com.levelupacademy.models.reader.CourseReader;
import br.com.levelupacademy.models.reader.SubcategoryReader;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.video.Video;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Category category = new Category("categoria", "abcd1", "descrição", "guia de estudos", false, 1, "#linkdaimagem.com", "#fff");
        Subcategory subcategory = new Subcategory("subcategoria", "aa52z", "descrição", "guia de estudos", false, 1, category);
        Course java = new Course("Java", "1254", 20, "Iniciantes", true, "Sérgio", "ementa", "OOP", subcategory);
        Section section = new Section("Nome", "3", 1, java);
        Video video = new Video("video", "1", 4, section, "www.alura.com.br", 5, "transcrição");
        Question question = new Question("dúvida", "1ab", 1, section, "enunciado");
        Alternative alternative = new Alternative("texto", 1, true, "ta certa", question);
        Explanation explanation = new Explanation("explicacao", "mza1", 1, section, "texto");

//        System.out.println(java);
//        System.out.println(section);
//        System.out.println(video);
//        System.out.println(question);
//        System.out.println(alternative);
//        System.out.println(category);
//        System.out.println(subcategory);
//        System.out.println(explanation);
        try {
            CategoryReader categoryReader = new CategoryReader();
            List<Category> categories = categoryReader.readArchive("/home/matheus/Documentos/entradas/categoria.csv");
            categories.forEach(c -> System.out.println(c));
            System.out.println("==================");
            List<Category> categoriesActive = categoryReader.showActiveCategories(categories);
            categoriesActive.forEach(c -> System.out.println("Categorias ativas: " + c));
            System.out.println("\n" + "============================");
            SubcategoryReader subcategoryReader = new SubcategoryReader();
            List<Subcategory> subcategories = subcategoryReader.readArchive("/home/matheus/Documentos/entradas/subcategoria.csv", categories);
            subcategories.forEach(s -> System.out.println(s));
            System.out.println("==================");
            List<Subcategory> subcategoryList = subcategoryReader.showSubcategoriesWithoutDescription(subcategories);
            subcategoryList.forEach(s -> System.out.println("Subcategorias sem descrição " + s));
            System.out.println("\n" + "============================");
            CourseReader courseReader = new CourseReader();
            List<Course> courses = courseReader.readArchive("/home/matheus/Documentos/entradas/curso.csv", subcategories);
            courses.forEach(c -> System.out.println(c));

            HtmlWriter htmlWriter = new HtmlWriter();
            htmlWriter.outputWriter(categories, subcategories, courses);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}