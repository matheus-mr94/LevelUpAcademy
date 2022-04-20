package br.com.levelupacademy.config.security;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryLoginResponse;
import br.com.levelupacademy.models.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;

    public LoginController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        List<Category> categoryList = categoryRepository.findActiveCategoriesWithPublicCourses();
        List<CategoryLoginResponse> categories = CategoryLoginResponse.toDTO(categoryList);

        model.addAttribute("categories", categories);

        return "admin/formLogin";
    }







}
