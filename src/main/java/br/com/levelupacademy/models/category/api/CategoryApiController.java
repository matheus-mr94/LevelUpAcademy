package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.category.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    public CategoryApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "listOfCategories")
    @GetMapping( value = "/api/categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CategoryApiResponse>> completeResponse() {
        List<Category> categories = categoryRepository.findAllByActiveTrue();
        List<CategoryApiResponse> categoryApiResponses = CategoryApiResponse.toDTO(categories);

        return ResponseEntity.ok(categoryApiResponses);
    }

    @CacheEvict(value = "listOfCategories", allEntries = true)
    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    public ResponseEntity<String> cacheCleaner() {
        return new ResponseEntity<String>("Cache foi limpo", HttpStatus.OK);
    }
}
