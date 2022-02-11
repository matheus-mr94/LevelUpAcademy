package br.com.levelupacademy.test;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.video.Video;

public class Test {
    public static void main(String[] args) {
        Course java = new Course("Java", "12", 5, "iniciantes", "sergio", "emeneta", "orientacao a objetos");
        Section section = new Section("Nome", "123",1, java);
        //Video video = new Video("titulo", "alura-cursos", 1, "www.alura.com.br", 10, "transcricao");

    }
}