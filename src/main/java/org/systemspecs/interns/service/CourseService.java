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
public interface CourseService {

    Course getACourse(String course_code);


}
