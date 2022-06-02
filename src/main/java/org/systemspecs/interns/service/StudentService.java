package org.systemspecs.interns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Student;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {


    public List<Course> getAllCourses(Long studentId);



    public void addCourseToStudent(String fullName, List<String> course_list);

    public void deleteCourseFromStudent(String fullName, String course_code);

}

