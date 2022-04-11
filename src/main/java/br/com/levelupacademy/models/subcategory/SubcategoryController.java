package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryRepository;
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
import java.util.Comparator;
import java.util.List;

@Controller
public class SubcategoryController {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryController(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping( "/admin/subcategories/{categoryCode}")
    public String simpleResponse(@PathVariable String categoryCode, Model model) {
       Category category = categoryRepository.findByCode(categoryCode)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       List<Subcategory> subcategories = category.getSubcategories().stream()
               .sorted(Comparator.comparing(Subcategory::getSequence)).toList();

       List<SubcategorySimpleResponse> simpleResponseList = SubcategorySimpleResponse.toDTO(subcategories);

       model.addAttribute("category", category);
       model.addAttribute("subcategories", simpleResponseList);

       return "subcategory/listSubcategories";
    }

    @GetMapping("/admin/subcategories/new")
    public String  getFormToCreateSubcategory(SubcategoryCreateRequest request, Model model) {
        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", request);

        return "subcategory/createSubcategoryForm";
    }

    @PostMapping("/admin/subcategories")
    @Transactional
    public String createSubcategory(@Valid SubcategoryCreateRequest request, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getFormToCreateSubcategory(request, model);
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );
        Subcategory subcategory = request.toEntity(category);
        subcategoryRepository.save(subcategory);

        return "redirect:/admin/subcategories/" + category.getCode();
    }
}
