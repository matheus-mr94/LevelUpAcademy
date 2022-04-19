package br.com.levelupacademy.models.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String getFormToCreateCategory(CategoryCreateRequest categoryRequest, Model model) {
        model.addAttribute("category", categoryRequest);
        return "category/createCategoryForm";
    }

    @PostMapping("/admin/categories")
    @Transactional
    public String createCategory(@Valid CategoryCreateRequest categoryRequest, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getFormToCreateCategory(categoryRequest, model);
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

        return "category/updateCategoryForm";
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

    @PostMapping("/admin/category/changeStatus/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        category.disable();
        categoryRepository.save(category);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryCode}")
    public String getCategoryPage(@PathVariable String categoryCode, Model model) {
        Category category = categoryRepository.findActiveCategoriesWithPublicCoursesByCategoryCode(categoryCode)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CategoryPageResponse categoriePage = new CategoryPageResponse(category);

        model.addAttribute("category", categoriePage);
        //TODO query com problema
        return "category/categoryPage";
    }
}
