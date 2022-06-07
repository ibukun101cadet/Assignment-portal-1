package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.dto.ResponseData;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.StudentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/student")

public class StudentController {
    private final StudentService studentService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public StudentController(StudentService studentService,AssignmentSubmissionService assignmentSubmissionService) {
        this.studentService = studentService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }


    @GetMapping
    public String homepage(){
        return "Welcome";
    }

    //courses automatically added
    @PostMapping("/add_courses")
    public ResponseEntity<?> addCoursesToStudent(@RequestBody CourseToStudent form){//?
        studentService.addCourseToStudent(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();
    }


    @GetMapping("{studentId}/courses")
    public ResponseEntity<GenericResponse> getAllCourses(@PathVariable("studentId")Long studentId){
        System.out.println("viewing all student courses");

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");
        response.setData(studentService.getAllCoursesTaken(studentId));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //courses automatically deleted i.e admin
    @DeleteMapping("/{fullName}/unenroll/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code, @PathVariable("fullName")String fullName){
        studentService.deleteCourseFromStudent(fullName, course_code);
    }








}




