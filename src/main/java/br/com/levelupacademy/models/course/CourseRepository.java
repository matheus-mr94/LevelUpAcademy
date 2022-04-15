package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query(nativeQuery = true, value = """
            SELECT c.instructor as instructor,
            COUNT(*) as totalOfCourses
            FROM Course c
            GROUP BY c.instructor
            ORDER BY COUNT(*) DESC LIMIT 1;
            """)
    InstructorProjection getInstructorWithMoreCourses();

    Page<Course> findAllBySubcategory(Subcategory subcategory, Pageable pageable);

    Optional<Course> findByCode(String courseCode);
}
