package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.subcategory.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseCreateRequestValidator createRequestValidator;
    private final CourseUpdateRequestValidator updateRequestValidator;

    public CourseController(CourseRepository courseRepository, SubcategoryRepository subcategoryRepository, CourseCreateRequestValidator createRequestValidator, CourseUpdateRequestValidator updateRequestValidator) {
        this.courseRepository = courseRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.createRequestValidator = createRequestValidator;
        this.updateRequestValidator = updateRequestValidator;
    }

    @InitBinder("courseCreateRequest")
    void initBinderCreateRequest(WebDataBinder dataBinder) {
        dataBinder.addValidators(createRequestValidator);
    }

    @InitBinder("courseUpdateRequest")
    void initBinderUpdateRequest(WebDataBinder dataBinder) {
        dataBinder.addValidators(updateRequestValidator);
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

    @GetMapping("/admin/courses/new")
    public String getFormToCreateCourse(CourseCreateRequest createRequest, Model model) {
        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByNameAsc();

        model.addAttribute("subcategories", subcategories);
        model.addAttribute("course", createRequest);

        return "course/createCourseForm";
    }

    @PostMapping("/admin/courses")
    @Transactional
    public String createCourse(@Valid CourseCreateRequest createRequest, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getFormToCreateCourse(createRequest, model);
        }
        Subcategory subcategory = subcategoryRepository.findById(createRequest.getSubcategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Course course = createRequest.toEntity(subcategory);
        courseRepository.save(course);

        return String.format("redirect:/admin/courses/%s/%s" , course.getCategoryCode(),
                course.getSubcategoryCode());
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String getCourseToUpdate(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                                    @PathVariable String courseCode,
                                    CourseUpdateRequest updateRequest, Model model ) {

        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByNameAsc();
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        CourseUpdateRequest courseUpdateRequest = new CourseUpdateRequest(course);

        model.addAttribute("subcategories", subcategories);
        model.addAttribute("course", courseUpdateRequest);

        return "course/updateCourseForm";
    }

    @PostMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    @Transactional
    public String updateCourse(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                               @PathVariable String courseCode,
                               @Valid CourseUpdateRequest courseUpdateRequest, BindingResult result,
                               Model model) {
        if(result.hasErrors()) {
            return getCourseToUpdate(categoryCode, subcategoryCode, courseCode, courseUpdateRequest, model);
        }
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Subcategory subcategory = subcategoryRepository.findById(courseUpdateRequest.getSubcategoryId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        course.update(courseUpdateRequest, subcategory);
        courseRepository.save(course);

        return String.format("redirect:/admin/courses/%s/%s" , course.getCategoryCode(),
                course.getSubcategoryCode());

    }
}
