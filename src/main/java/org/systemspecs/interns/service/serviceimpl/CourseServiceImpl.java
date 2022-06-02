package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.service.CourseService;

import java.util.List;

@Service

@Transactional

public class CourseServiceImpl implements CourseService {

    private final CourseRepo course_repo;


    public CourseServiceImpl(CourseRepo course_repo) {
        this.course_repo = course_repo;
    }




    @Override
    public Course getACourse(String course_code) {
        return course_repo.findByCode(course_code);
    }

    @Override
    public List<AssignmentUpload> getByAssignmentCourseCode(String course_code) {
        return course_repo.findassignmentbyCode(course_code);
    }

}