package org.systemspecs.interns.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.dto.ResponseData;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.AssignmentUploadService;
import org.systemspecs.interns.service.CourseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/course")

public class CourseController {

    private final CourseService courseService;
    private final AssignmentUploadService assignmentUploadService;

    public CourseController(CourseService courseService,
                            AssignmentUploadService assignmentUploadService) {
        this.courseService = courseService;
        this.assignmentUploadService = assignmentUploadService;
    }

    @GetMapping("/{course_code}")
    public ResponseEntity<GenericResponse> findACourseByCourseCode(
            @PathVariable("course_code")String course_code){
        System.out.println("finding a course ");

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");
        response.setData(courseService.getCourseByCourseCode(course_code));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//this!!
    @PostMapping("/{courseId}/uploadAssignment")
    public ResponseEntity<GenericResponse> uploadAssignments(@PathVariable("courseId")Long courseId,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam("title") String assignmentTitle,
                                  @RequestParam("dueDate") String dueDate) throws IOException {
        System.out.println("uploading course assignment");
        AssignmentUpload assignmentUpload =
                   assignmentUploadService.uploadAssignment(file,assignmentTitle, dueDate, courseId);
        String downloadURl = "";

        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(assignmentUpload.getAssignmentUploadId()))
                .toUriString();

        ResponseData responseData = new ResponseData(
                assignmentUpload.getAssignmentTitle(),
                downloadURl,
                file.getContentType(),
                file.getSize());

        GenericResponse response = new GenericResponse();

        response.setCode("00");
        response.setMessage("Assignment successfully uploaded");
        response.setData(responseData);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{course_code}/assignments")
    public ResponseEntity<GenericResponse> viewAllCourseAssignments(
            @PathVariable("course_code")String course_code){
        System.out.println("viewing all course assignments");

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");
        response.setData(courseService.getAllAssignmentsByCourseCode(course_code));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}




