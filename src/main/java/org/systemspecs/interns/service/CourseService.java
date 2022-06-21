package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.AppUser;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;

@Service
public interface CourseService {

    Course getCourseByCourseCode(String course_code);

    Course getCourseById(Long courseId);

    List<AssignmentUpload> getAllAssignmentsByCourseCode(String course_code);

    void addParticipant(Long courseId, String fullname);

    void removeParticipant(String course_code, String fullname);

    List<AppUser> getParticipantsByRole(Long courseId, String role);

    List<AppUser> getParticipants(Long courseId);

}
