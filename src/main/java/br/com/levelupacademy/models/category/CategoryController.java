package br.com.levelupacademy.models.category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String simpleResponse(Model model) {
        List<Category> categories = categoryRepository.findAllByOrderBySequence();
        List<CategorySimpleResponse> simpleResponse = CategorySimpleResponse.toDTO(categories);
        model.addAttribute("categories", simpleResponse);

        return "category/listCategories";
    }

    @GetMapping("/admin/categories/new")
    public String getFormToCreate(CategoryCreateRequest categoryRequest, Model model) {
        model.addAttribute("category", categoryRequest);
        return "category/createCategory";
    }

    @PostMapping("/admin/categories")
    @Transactional
    public String createCategory(@Valid CategoryCreateRequest categoryRequest, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getFormToCreate(categoryRequest, model);
        }
        Category category = categoryRequest.toEntity();
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String getCategoryToUpdate(@PathVariable String code, Model model) {
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest(category);
        model.addAttribute("category", categoryUpdateRequest);

        return "category/updateCategory";
    }

    @PostMapping("/admin/categories/{code}")
    @Transactional
    public String updateCategory(@PathVariable String code, @Valid CategoryUpdateRequest categoryUpdateRequest, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getCategoryToUpdate(code,model);
        }
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.update(categoryUpdateRequest);
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }
}
