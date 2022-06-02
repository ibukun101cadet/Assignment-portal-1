package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;

@Service
public interface LecturerService {


    public List<Course> getAllCoursesTaught(Long lecturerId);


    public void addCourseToLecturer(String fullName, List<String> course_list);

    public void deleteCourseFromLecturer(String fullName, String course_code);

    public void uploadAssignment(String course_code, List<AssignmentUpload> assignments);


}
