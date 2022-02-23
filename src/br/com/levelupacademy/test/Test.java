package br.com.levelupacademy.test;

import br.com.levelupacademy.models.alternative.Alternative;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.explanation.Explanation;
import br.com.levelupacademy.models.output.Output;
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
    public static void main(String[] args) throws IOException {
        Category category = new Category("categoria", "abcd1", "descrição", "guia de estudos", false, 1, "#linkdaimagem.com","#fff");
        br.com.levelupacademy.models.subcategory.Subcategory subcategory = new br.com.levelupacademy.models.subcategory.Subcategory("subcategoria", "aa52z", "descrição", "guia de estudos", false, 1, category);
        Course java = new Course("Java","1254",20,"Iniciantes",true,"Sérgio","ementa","OOP",subcategory);
        Section section = new Section("Nome", "3",1, java);
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

        CategoryReader categoryReader = new CategoryReader();
        List<Category> categories = categoryReader.readArchive("/home/matheus/Documentos/entradas/categoria.csv");
        System.out.println("\n" + "============================");
        SubcategoryReader subcategoryReader = new SubcategoryReader();
        List<Subcategory> subcategories = subcategoryReader.readArchive("/home/matheus/Documentos/entradas/subcategoria.csv", categories);
        System.out.println("\n" + "============================");
        CourseReader courseReader = new CourseReader();
        List<Course> courses = courseReader.readArchive("/home/matheus/Documentos/entradas/curso.csv", subcategories);

        Output output = new Output();
        output.outputWriter(categories,subcategories,courses);


    }
}