package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/listaCategorias")
    public String teste() {
        return "listCategories";
    }
}
