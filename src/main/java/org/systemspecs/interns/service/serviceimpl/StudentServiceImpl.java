package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Student;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.repository.StudentRepo;
import org.systemspecs.interns.service.StudentService;

import java.util.List;

@Service

@Transactional

public class StudentServiceImpl implements StudentService {

    private final StudentRepo repo;
    private final CourseRepo course_repo;


    public StudentServiceImpl(StudentRepo repo, CourseRepo course_repo) {
        this.repo = repo;
        this.course_repo = course_repo;
    }



    @Override
    public List<Course> getAllCourses(Long studentId) {
        return repo.findCoursesById(studentId);
    }

    @Override
    public Course getACourse(String course_code) {
        return course_repo.findByCode(course_code);
    }

    @Override
    public void addCourseToStudent(String fullName, List<String> course_list) {
        {
            Student student = repo.findByFullName(fullName);

            for (String s : course_list) {
                Course course = course_repo.findByCode(s);

                student.getCourses().add(course);
            }
        };
    }

}
