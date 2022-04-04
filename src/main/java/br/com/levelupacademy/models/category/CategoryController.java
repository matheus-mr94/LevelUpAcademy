package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Controller;
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
public class CategoryController implements WebMvcConfigurer {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/categories");
    }

    @GetMapping
    public String simpleResponse(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Category> categoriesOrdered = categories.stream().sorted(Comparator.comparingInt(Category::getSequence)).toList();
        List<CategorySimpleResponse> simpleRequestList = new ArrayList<>();
        categoriesOrdered.forEach( c -> {
            CategorySimpleResponse csr = new CategorySimpleResponse(c);
            simpleRequestList.add(csr);
        });
        model.addAttribute("categories", simpleRequestList);
        return "listCategories";
    }

    @GetMapping("/new")
    public String getFormToCreate() {
        return "createCategory";
    }

    @PostMapping
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
    public String updateCategory(@PathVariable String code, @Valid CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryUpdateRequest.toEntity();
        category.update(categoryUpdateRequest);
        categoryRepository.save(category);

        return "redirect:/admin/categories";

    }

}
