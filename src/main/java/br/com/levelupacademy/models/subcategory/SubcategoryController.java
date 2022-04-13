package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryRepository;
import br.com.levelupacademy.models.category.CategoryUpdateRequest;
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

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String getSubcategoryToUpdate(@PathVariable String categoryCode,
                                      @PathVariable String subcategoryCode, Model model) {
        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        SubcategoryUpdateRequest subcategoryUpdateRequest = new SubcategoryUpdateRequest(subcategory);

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();

        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", subcategoryUpdateRequest);

        return "subcategory/updateSubcategoryForm";
    }

    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    @Transactional
    public String updateSubcategory(@PathVariable String categoryCode,
                                    @PathVariable String subcategoryCode,
                                    @Valid SubcategoryUpdateRequest subcategoryUpdateRequest,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            return getSubcategoryToUpdate(categoryCode, subcategoryCode ,model);
        }
        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findById(subcategoryUpdateRequest.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        subcategory.update(subcategoryUpdateRequest, category);
        subcategoryRepository.save(subcategory);

        return String.format("redirect:/admin/subcategories/%s" , category.getCode());

    }

    @PostMapping("/admin/subcategory/changeStatus/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        subcategory.toggleStatus();
        subcategoryRepository.save(subcategory);

        return ResponseEntity.ok().build();
    }
}
