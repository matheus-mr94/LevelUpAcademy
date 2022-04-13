package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.category.api.CourseApiSimpleResponse;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.subcategory.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CourseController(CourseRepository courseRepository, SubcategoryRepository subcategoryRepository) {
        this.courseRepository = courseRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String simpleResponse(@PathVariable String categoryCode,
                                 @PathVariable String subcategoryCode,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {

        PageRequest pageRequest = PageRequest.of(page == 0 ? page : page-1, 5);
        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       Page<CourseSimpleResponse> courses = courseRepository.findAllBySubcategory(subcategory, pageRequest)
               .map(CourseSimpleResponse::new);

       model.addAttribute("subcategory", subcategory);
       model.addAttribute("courses", courses);

        return "course/listCourses";
    }
}
