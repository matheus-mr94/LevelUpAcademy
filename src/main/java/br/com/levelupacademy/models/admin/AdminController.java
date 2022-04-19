package br.com.levelupacademy.models.admin;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryLoginResponse;
import br.com.levelupacademy.models.category.CategoryProjection;
import br.com.levelupacademy.models.category.CategoryRepository;
import br.com.levelupacademy.models.course.CourseRepository;
import br.com.levelupacademy.models.course.InstructorProjection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    public AdminController(CategoryRepository categoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin")
    public String adminPanel() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        List<CategoryProjection> categories = categoryRepository.countCoursesByCategory();
        InstructorProjection instructor = courseRepository.getInstructorWithMoreCourses();

        model.addAttribute("categories", categories);
        model.addAttribute("instructor", instructor);
        return "/admin/adminDashboard";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        List<Category> categoryList = categoryRepository.findActiveCategoriesWithPublicCourses();
        List<CategoryLoginResponse> categories = CategoryLoginResponse.toDTO(categoryList);

        model.addAttribute("categories", categories);
        //TODO query com problema
        return "admin/formLogin";
    }
}
