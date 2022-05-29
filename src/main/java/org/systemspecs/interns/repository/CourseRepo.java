package org.systemspecs.interns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.systemspecs.interns.domain.Course;


public interface CourseRepo extends JpaRepository<Course, Long> {

//    @Query("SELECT c FROM Course c WHERE c.course_title=?1")
//    Course findByTitle(String course_title);
    @Query("SELECT c FROM Course c WHERE c.course_code=?1")
    Course findByCode(String course_code);

}
