package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String simpleResponse(Model model) {
        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        model.addAttribute("categories", categories);
        return "listCategories";
    }

    @GetMapping("/new")
    public String getFormToCreate() {
        return "createCategory";
    }

    @PostMapping
    @Transactional
    public String createCategory(@Valid CategoryCreateRequest categoryRequest) {
        Category category = categoryRequest.toEntity();
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{code}")
    public String getCategoryToUpdate(@PathVariable String code, Model model) {
        Category category = categoryRepository.findByCode(code);
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest(category);

        model.addAttribute("category", categoryUpdateRequest);
        return "updateCategory";
    }

    @PostMapping("/{code}")
    @Transactional
    public String updateCategory(@PathVariable String code, @Valid CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryUpdateRequest.toEntity();
        category.update(categoryUpdateRequest);
        categoryRepository.save(category);

        return "redirect:/admin/categories";

    }

}
