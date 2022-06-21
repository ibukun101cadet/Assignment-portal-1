package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemspecs.interns.domain.AppUser;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.domain.Role;
import org.systemspecs.interns.repository.AppUserRepo;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.service.CourseService;


import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional

public class CourseServiceImpl implements CourseService {

    private final CourseRepo course_repo;
    private final AppUserRepo appUserRepo;

    public CourseServiceImpl(CourseRepo course_repo, AppUserRepo appUserRepo) {
        this.course_repo = course_repo;
        this.appUserRepo = appUserRepo;
    }

    @Override
    public Course getCourseByCourseCode(String course_code) {
        return course_repo.findByCode(course_code);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return course_repo.findById(courseId).get();
    }

    @Override
    public List<AssignmentUpload> getAllAssignmentsByCourseCode(String course_code) {
        return course_repo.findAssignmentsByCode(course_code);
    }

    @Override
    public void addParticipant(Long courseId, String fullname) {
        Course course = course_repo.findById(courseId).get();
        AppUser user = appUserRepo.findByFullname(fullname);
        user.getCourses().add(course);
        course.getParticipants().add(user);
    }

    @Override
    public void removeParticipant(String course_code, String fullname) {
        Course course = course_repo.findByCode(course_code);
        AppUser user = appUserRepo.findByFullname(fullname);
        course.getParticipants().remove(user);


    }

    @Override
    public List<AppUser> getParticipantsByRole(Long courseId, String role) {
        Course course = course_repo.findById(courseId).get();
        List<AppUser> allParticipants = course.getParticipants();
        List<AppUser> participantsByRole;


        participantsByRole = allParticipants.stream()
                .filter(appUser -> appUser.getRole().equals(Role.valueOf(role))
                )
                .collect(Collectors.toList());
        return participantsByRole;
    }

    @Override
    public List<AppUser> getParticipants(Long courseId) {
        Course course = course_repo.findById(courseId).get();
        return course.getParticipants();
    }


}