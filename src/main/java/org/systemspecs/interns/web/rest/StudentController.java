//package org.systemspecs.interns.web.rest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.systemspecs.interns.dto.response.GenericResponse;
//import org.systemspecs.interns.service.StudentService;
//
//@RestController
//@RequestMapping(path = "/api/student")
//
//public class StudentController {
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//
//    }
//
//
//    @GetMapping
//    public String homepage(){
//        return "Welcome";
//    }
//
//
//    @GetMapping("{studentId}/courses")
//    public ResponseEntity<GenericResponse> getAllCourses(@PathVariable("studentId")Long studentId){
//        System.out.println("Viewing all student courses");
//
//        GenericResponse response = new GenericResponse();
//        response.setCode("00");
//        response.setMessage("success");
//        response.setData(studentService.getAllCoursesTaken(studentId));
//        response.setMetadata(null);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//}




