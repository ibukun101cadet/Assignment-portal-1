package org.systemspecs.interns.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Course_assignment;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.service.CourseService;
import org.systemspecs.interns.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }




    @GetMapping("/course/{course_code}")
    public Course findACourse(@PathVariable("course_code")String course_code){
        return courseService.getACourse(course_code);
    }



}




