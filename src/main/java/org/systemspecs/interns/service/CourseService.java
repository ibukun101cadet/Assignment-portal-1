package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;

@Service
public interface CourseService {

    Course getACourse(String course_code);

    List<AssignmentUpload> getByAssignmentCourseCode(String course_code);


}
