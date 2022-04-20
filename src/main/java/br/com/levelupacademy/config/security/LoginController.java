package br.com.levelupacademy.config.security;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryDTOResponse;
import br.com.levelupacademy.models.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;
    private final AuthenticationService authenticationService;

    public LoginController(CategoryRepository categoryRepository, AuthenticationService authenticationService) {
        this.categoryRepository = categoryRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        List<Category> categoryList = categoryRepository.findActiveCategoriesWithPublicCourses();
        List<CategoryDTOResponse> categories = CategoryDTOResponse.toDTO(categoryList);

        model.addAttribute("categories", categories);

        return "admin/formLogin";
    }
}
