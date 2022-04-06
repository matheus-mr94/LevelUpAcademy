package br.com.levelupacademy.models.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category, Long> {

    List<Category> findAllByActiveTrue();

    Optional<Category> findByCode(String code);

    List<Category> findAllByOrderBySequence();
}
