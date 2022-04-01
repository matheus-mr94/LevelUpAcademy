package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/admin/categories")
    public String listCategoriesOrdered(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Category> categoriesOrdered = categories.stream().sorted(Comparator.comparingInt(Category::getSequence)).toList();
        Model categoryList = model.addAttribute("categories", categoriesOrdered);
        return "listCategories";
    }
}
