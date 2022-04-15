package br.com.levelupacademy.models.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByCode(String subcategoryCode);

    List<Subcategory> findAllByOrderByNameAsc();
}
