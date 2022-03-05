package br.com.levelupacademy.models.output;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class SqlWriter {

    public static void queryWriter(List<Category> categories, List<Subcategory> subcategories, List<Course> courses) throws IOException {

        String queryForCategory = "";

        for(Category category : categories) {
            queryForCategory += String.format("""
                    INSERT INTO 
                    `Category`(`name`, `code`, `description`, study_guide, `active`, `sequence`, url_image, hex_code)
                    VALUES('%s', '%s', '%s', '%s', %s, %d, '%s', '%s');
                    
                    """,category.getName(), category.getCode(), category.getDescription(),
                    category.getStudyGuide(), category.isActive(), category.getSequence(),
                    category.getUrlImage(), category.getHexCode());

        }

        String queryForSubcategory = "";

        for(Subcategory subcategory : subcategories) {
            queryForSubcategory += String.format("""
                    INSERT INTO 
                    `Subcategory`(`name`,`code`,`description`,study_guide, `active`, sequence, category_code)
                    VALUES('%s', '%s', '%s', '%s', %s, %d, (SELECT `code` FROM Category WHERE `code` = '%s'));

                    """, subcategory.getName(), subcategory.getCode(), subcategory.getDescription(),
                    subcategory.getStudyGuide(), subcategory.isActive(), subcategory.getSequence(),
                    subcategory.getCategoryCode());
        }

        String queryForCourse = "";

        for(Course course : courses) {
            queryForCourse += String.format("""
                    INSERT INTO 
                    `Course`(`name`, `code`, estimated_time_in_hours, visible, target, instructor ,syllabus, 
                    developed_skills, subcategory_code)
                    VALUES("%s", "%s", %d, %s, "%s", "%s", "%s", "%s", (SELECT `code` FROM Subcategory WHERE `code` = '%s'));
                    
                    """, course.getName(), course.getCode(), course.getEstimatedTimeInHours(), course.isVisible(),
                    course.getTarget(), course.getInstructor(), course.getSyllabus(), course.getDevelopedSkills(),
                    course.getSubcategoryCode());
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teste.sql"));
        String generalQuery = queryForCategory + queryForSubcategory + queryForCourse;
        bufferedWriter.write(generalQuery);
        bufferedWriter.close();
    }

}