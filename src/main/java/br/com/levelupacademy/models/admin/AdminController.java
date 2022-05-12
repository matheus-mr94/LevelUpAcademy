package br.com.levelupacademy.models.admin;

import br.com.levelupacademy.models.category.CategoryProjection;
import br.com.levelupacademy.models.category.CategoryRepository;
import br.com.levelupacademy.models.course.CourseRepository;
import br.com.levelupacademy.models.course.InstructorProjection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

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
}
