package br.com.levelupacademy.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

    List<Category> findAllByActiveTrue();

    Optional<Category> findByCode(String code);

    List<Category> findAllByOrderBySequence();

    List<Category> findAllByOrderByNameAsc();

    @Query(nativeQuery = true, value = """
    SELECT ca.name as name, COUNT(co.id) as countOfCourses
    FROM Category ca
    LEFT JOIN Subcategory su ON ca.id = su.category_id
    LEFT JOIN Course co ON su.id = co.subcategory_id
    GROUP BY ca.name
    ORDER BY COUNT(co.id) DESC;""")
    List<CategoryProjection> countCoursesByCategory();

    @Query("""
       SELECT DISTINCT ca FROM Category ca
       JOIN FETCH  ca.subcategories s 
       JOIN s.courses co 
       WHERE ca.active = true AND s.active = true AND co.visible = true
       ORDER BY ca.sequence, s.sequence
    """)
    List<Category> findActiveCategoriesWithPublicCourses();

    @Query("""
       SELECT DISTINCT ca FROM Category ca
       JOIN FETCH  ca.subcategories s 
       JOIN s.courses co 
       WHERE ca.active = true AND s.active = true AND co.visible = true AND ca.code = ?1
       ORDER BY ca.sequence, s.sequence
    """)
    Optional<Category> findActiveCategoriesWithPublicCoursesByCategoryCode(String code);
}
