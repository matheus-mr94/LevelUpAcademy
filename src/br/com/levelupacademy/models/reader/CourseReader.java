package br.com.levelupacademy.models.reader;


import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.validators.CourseValidations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.levelupacademy.validators.Validations.verifyIntegerNumber;

public class CourseReader {

    public void readArchive(String filePath, List<br.com.levelupacademy.models.subcategory.Subcategory> subcategories) throws FileNotFoundException {
        try {

            List<Course> courses = new ArrayList<>();
            File archive = new File(filePath);
            Scanner scan = new Scanner(archive, "UTF-8");
            scan.nextLine();

            while (scan.hasNext()) {
                String line = scan.nextLine();

                if (!line.isEmpty()) {
                    String[] courseData = line.split(",", 9);
                    String name = courseData[0];
                    String code = courseData[1].trim();
                    String estimatedTime = courseData[2];
                    int estimatedTimeInHours = verifyIntegerNumber(estimatedTime);
                    boolean visible = courseData[3].equals("PÚBLICA") ? true : false;
                    String target = courseData[4];
                    String instructor = courseData[5];
                    String syllabus = courseData[6];
                    String developedSkills = courseData[7];
                    String subcategoryCode= courseData[8];

                    Subcategory subcategory = null;
                    for (Subcategory sub : subcategories) {
                        if(subcategoryCode.equals(sub.getCode())) {
                            subcategory = sub;
                        }
                    }

                    if(CourseValidations.courseIsValid(name,code,instructor,subcategory)) {
                        Course course = new Course(name, code, estimatedTimeInHours, target, visible, instructor, syllabus, developedSkills, subcategory);
                        courses.add(course);
                    }
                }
            }
                scan.close();
                for (Course course : courses) {
                    System.out.println(course);
                }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");

        }
    }
}