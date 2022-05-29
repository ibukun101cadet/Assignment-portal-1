package org.systemspecs.interns.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Student;

import java.util.List;


public interface StudentRepo extends JpaRepository<Student, Long> {


    @Query("SELECT s FROM Student s WHERE s.fullName=?1")
    Student findByFullName(String fullName);

    @Query("SELECT s.courses FROM Student s WHERE s.studentId=?1")
    List<Course> findCoursesById(Long studentId);
}
