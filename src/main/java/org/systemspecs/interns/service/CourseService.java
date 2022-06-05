package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;

@Service
public interface CourseService {

    Course getCourseByCourseCode(String course_code);

    List<AssignmentUpload> getAllAssignmentsByCourseCode(String course_code);


}
