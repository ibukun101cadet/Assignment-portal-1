package org.systemspecs.interns.web.rest;

import org.springframework.web.bind.annotation.*;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }




    @GetMapping("/course/{course_code}")
    public Course findACourseByCourseCode(@PathVariable("course_code")String course_code){
        return courseService.getACourse(course_code);
    }

//    @GetMapping("assignments/course/{course_code}")
//    public List<AssignmentUpload> findACourseAssignments(@PathVariable("course_code")String course_code){
//        return courseService.getByAssignmentCourseCode(course_code);
//    }



}




