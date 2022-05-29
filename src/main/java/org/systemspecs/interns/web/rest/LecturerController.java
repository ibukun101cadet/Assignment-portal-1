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
import org.systemspecs.interns.service.LecturerService;
import org.systemspecs.interns.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class LecturerController {
    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }


    @GetMapping("/courses/lecturer_id/{lecturerId}")
    public List<Course> getAllCoursesTaught(@PathVariable("lecturerId")Long lecturerId){
        return lecturerService.getAllCoursesTaught(lecturerId);
    }

    @PostMapping("/lecturer/add_courses")
    public ResponseEntity<?> addCoursesToLecturer(@RequestBody CourseToLecturer form){//?
        lecturerService.addCourseToLecturer(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();

    }



//    @PostMapping("/uploadFiles")
//    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
//        for (MultipartFile file: files){
//            assignmentService.saveFile(file);
//
//        }
//        return "redirect:/";
//
//    }
//
//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
//        Course_assignment assignment = assignmentService.getFile(fileId).get();
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(assignment.getDocType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+assignment.getDocName()+"\"")
//                .body(new ByteArrayResource(assignment.getContent()));
//    }


    @Data
    class CourseToLecturer{
        public CourseToLecturer() {
        }

        private String fullName;


        public String getFullName() {
            return fullName;
        }

        public List<String> getCourse_list() {
            return course_list;
        }

        private List<String> course_list;
    }

}




