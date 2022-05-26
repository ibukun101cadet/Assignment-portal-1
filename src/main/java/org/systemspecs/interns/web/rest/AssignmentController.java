package org.systemspecs.interns.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/assignment/")
@AllArgsConstructor
public class AssignmentController {

    @GetMapping("/submit")
    public String submitAssignment(){
        return "submit";
    }




}
