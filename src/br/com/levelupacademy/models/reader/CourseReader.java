package br.com.levelupacademy.models.reader;


import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.levelupacademy.validators.Validations.getIntegerNumberOrZeroFrom;

public class CourseReader {

    public List<Course> readArchive(String filePath, List<Subcategory> subcategories) throws FileNotFoundException {

            List<Course> courses = new ArrayList<>();
            try (Scanner scan = new Scanner(new File(filePath), "UTF-8")) {
            scan.nextLine();

            while (scan.hasNext()) {
                String line = scan.nextLine();

                if (!line.isEmpty()) {
                    String[] courseData = line.split(",", 9);
                    String name = courseData[0];
                    String code = courseData[1].trim();
                    String estimatedTime = courseData[2];
                    int estimatedTimeInHours = getIntegerNumberOrZeroFrom(estimatedTime);
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

                    try {
                        Course course = new Course(name, code, estimatedTimeInHours, target, visible, instructor, syllabus, developedSkills, subcategory);
                        courses.add(course);
                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<Course> findPrivateCourses(List<Course> courses) {
        return courses.stream().filter(c -> !c.isVisible()).toList();
    }

    public static Set<String> findInstructors(List<Course> courses) {
        return courses.stream().map(Course::getInstructor).collect(Collectors.toSet());
    }

    public static int numberOfCoursesFromInstructors(List<Course> courses, String instructorName) {
        return (int) courses.stream().filter(course -> course.getInstructor().equals(instructorName)).count();
    }

    public static Map<String,Integer> getCoursesAmountByInstructor(List<Course> courses) {
        return findInstructors(courses).stream().collect(Collectors.toMap(
                Function.identity(),
                instructor -> numberOfCoursesFromInstructors(courses, instructor)
        ));
       //return courses.stream().collect(Collectors.groupingBy(Course::getInstructor, Collectors.counting())); outro modo de resolução
    }
}