package br.com.levelupacademy.models.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
