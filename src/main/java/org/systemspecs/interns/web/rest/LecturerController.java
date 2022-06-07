package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.LecturerService;


import java.util.List;

@RestController
@RequestMapping(path = "/api/lecturer")

public class LecturerController {
    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;

    }


    @GetMapping("/{lecturerId}/courses/")
    public ResponseEntity<GenericResponse> getAllCoursesTaught(
            @PathVariable("lecturerId")Long lecturerId){
        System.out.println("getting all lecturer "+ lecturerId + " courses");

        GenericResponse response = new GenericResponse();

        response.setCode("00");
        response.setMessage("success");
        response.setData(lecturerService.getAllCoursesTaught(lecturerId));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }





//not necessarily necessary
    @PostMapping("/add_courses")
    public ResponseEntity<?> addCoursesToLecturer(@RequestBody CourseToLecturer form){
        lecturerService.addCourseToLecturer(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();

    }

    //not necessarily necessary
    @DeleteMapping("/{fullName}/remove/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code,
                             @PathVariable("fullName")String fullName){
        lecturerService.deleteCourseFromLecturer(fullName, course_code);
    }

}




