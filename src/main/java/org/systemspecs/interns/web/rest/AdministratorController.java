package org.systemspecs.interns.web.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.systemspecs.interns.domain.AppUser;

import org.systemspecs.interns.dto.CreateUserDTO;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.mapper.AppUserMapper;
import org.systemspecs.interns.service.AppUserService;
import org.systemspecs.interns.service.CourseService;

@RestController
@RequestMapping(path = "/api/admin")

public class AdministratorController {
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;
    private final CourseService courseService;

    public AdministratorController(AppUserService appUserService, AppUserMapper appUserMapper, CourseService courseService) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
        this.courseService = courseService;
    }


    @PostMapping("/addNewUser")
    public ResponseEntity<GenericResponse> addNewUser(@RequestBody CreateUserDTO createUserDTO){
        GenericResponse response = new GenericResponse();

        AppUser user = appUserMapper.toUser(createUserDTO);

        appUserService.save(user);

        response.setCode("00");
        response.setMessage("User has been added successfully");
        response.setData(null);
        response.setMetadata(null);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{fullname}")
    public ResponseEntity<GenericResponse> getParticipantByName(
            @PathVariable("fullname")String fullname)
            {
        System.out.println("Getting user" + fullname);

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");
        response.setData(appUserService.getUser(fullname));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("course/{courseId}/add_participant/{fullname}")
    public ResponseEntity<GenericResponse> addParticipantToCourse(@PathVariable("courseId")Long courseId,
                                                                  @PathVariable("fullname") String fullname){
        GenericResponse response = new GenericResponse();

        try {
        courseService.addParticipant(courseId, fullname);}


        catch(Exception e){
            response.setCode("00");
            response.setMessage("User not found");
            response.setData(null);
            response.setMetadata(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setCode("00");
        response.setMessage("successfully added user to course");
        response.setData(null);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{course_code}/removeParticipant/{fullname}")
    public ResponseEntity<GenericResponse>  removeParticipantFromCourse(
            @PathVariable("course_code")String course_code,
            @PathVariable("fullname")String fullname){
        GenericResponse response = new GenericResponse();
        try {
            courseService.removeParticipant(course_code, fullname);
        }

        catch(Exception e){
            response.setCode("00");
            response.setMessage("User not found");
            response.setData(null);
            response.setMetadata(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        response.setCode("00");
        response.setMessage("Removed" + course_code + "successfully");
        response.setData(null);
        response.setMetadata(null);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("course/{courseId}/get_participants/{role}")
    public ResponseEntity<GenericResponse> getParticipantsByRole(@PathVariable("courseId")Long courseId,
                                                                 @PathVariable("role")String role){

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");
        response.setData(courseService.getParticipantsByRole(courseId, role));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("course/{courseId}/get_participants")
    public ResponseEntity<GenericResponse> getParticipants(@PathVariable("courseId")Long courseId){
        System.out.println(courseService.getParticipants(courseId));
        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("success");

        response.setData(courseService.getParticipants(courseId));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}








