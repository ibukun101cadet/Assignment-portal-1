package org.systemspecs.interns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;


public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.course_code=?1")
    Course findByCode(String course_code);

    @Query("SELECT c.course_assignments FROM Course c WHERE c.course_code=?1")
    List<AssignmentUpload> findAssignmentsByCode(String course_code);

}
