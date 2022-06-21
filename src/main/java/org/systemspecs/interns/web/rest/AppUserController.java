package org.systemspecs.interns.web.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.AppUserService;

@RestController
@RequestMapping(path = "/api/lecturer")

public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;

    }


    @GetMapping("/{userId}/courses/")
    public ResponseEntity<GenericResponse> getAllUserCourses(
            @PathVariable("userId")Long userId){
        System.out.println("getting all user courses");

        GenericResponse response = new GenericResponse();

        response.setCode("00");
        response.setMessage("success");
        response.setData(appUserService.getAllUserCourses(userId));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}




