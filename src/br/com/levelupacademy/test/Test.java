package br.com.levelupacademy.test;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.alternative.Alternative;
import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.explanation.Explanation;
import br.com.levelupacademy.models.question.Question;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.video.Video;

public class Test {
    public static void main(String[] args) {
        Category category = new Category("categoria", "abcd1", "descrição", "guia de estudos", 1, "linkdaimagem.com", "#fff");
        Subcategory subcategory = new Subcategory("subcategoria", "aa52z", "descrição", "guia de estudos", false, 1, category);
        Course java = new Course("Java", "aab2", 5, "iniciantes", "sergio", "ementa", "orientacao a objetos",subcategory);
        Section section = new Section("Nome", "3",1, java);
        Video video = new Video("video", "1", 4, section, "www.alura.com.br", 5, "transcrição");
        Question question = new Question("dúvida", "1ab", 1, section, "enunciado");
        Alternative alternative = new Alternative("texto", 1, true, "ta certa", question);
        Explanation explanation = new Explanation("explicacao", "mza1", 1, section, "texto");

        System.out.println(java);
        System.out.println(section);
        System.out.println(video);
        System.out.println(question);
        System.out.println(alternative);
        System.out.println(category);
        System.out.println(subcategory);
        System.out.println(explanation);
    }
}