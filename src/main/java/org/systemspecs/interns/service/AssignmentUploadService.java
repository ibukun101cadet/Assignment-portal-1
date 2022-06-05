package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.io.IOException;

@Service
public interface AssignmentUploadService {

    void uploadAssignment(MultipartFile file, String assignmentTitle, String dueDate, Long courseId);

    AssignmentUpload getAssignmentUploadById(Long assignmentId);

    String updateAssignmentUpload(Long assignmentId, String docName, MultipartFile file) throws IOException;

//    public Optional<Course_assignment> getFile(Long fileId){
//        return repo.findById(fileId);
//
//    }
//    public List<Course_assignment> getFiles(){
//        return repo.findAll();
//    }
}
