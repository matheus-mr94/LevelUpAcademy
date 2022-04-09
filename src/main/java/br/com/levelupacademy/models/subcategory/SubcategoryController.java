package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
       model.addAttribute("category", category);
       model.addAttribute("subcategories", subcategories);

       return "subcategory/listSubcategories";
    }

    @GetMapping("/admin/subcategories/new")
    public String  getFormToCreate() {
        return "String";
    }



}
