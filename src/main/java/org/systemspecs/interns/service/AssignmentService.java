//package org.systemspecs.interns.service;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.domain.Course_assignment;
//import org.systemspecs.interns.repository.AssignmentRepo;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@NoArgsConstructor
//public class AssignmentService {
//    @Autowired
//    private AssignmentRepo repo;
//
//
//    public Course_assignment saveFile(MultipartFile file) {
//        String docname = file.getOriginalFilename();
//
//        try {
//            Course_assignment assignment = new Course_assignment(docname, file.getContentType(), file.getBytes());
//            return repo.save(assignment);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    return null;
//    }
//    public Optional<Course_assignment> getFile(Long fileId){
//        return repo.findById(fileId);
//
//    }
//    public List<Course_assignment> getFiles(){
//        return repo.findAll();
//    }
//}
