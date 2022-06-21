package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.repository.AssignmentUploadRepo;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.service.AssignmentUploadService;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service

@Transactional

public class AssignmentUploadServiceImpl implements AssignmentUploadService {

    private final CourseRepo repo;
    private final AssignmentUploadRepo assignmentRepo;



    public AssignmentUploadServiceImpl(CourseRepo repo,
                                       AssignmentUploadRepo assignmentRepo) {
        this.repo = repo;
        this.assignmentRepo = assignmentRepo;
    }

    @Override
    public AssignmentUpload uploadAssignment(MultipartFile file,
                                             String assignmentTitle,
                                             String dueDate,
                                             Long courseId) {
        AssignmentUpload assignment = null;
        {
            String title = assignmentTitle;

            LocalDateTime dateDue = LocalDateTime.parse(dueDate);
//            Duration duration = Duration.between(dateDue, LocalDateTime.now());
//
//            long timeInSeconds = duration.getSeconds();
//            int days =  (int) (timeInSeconds / 86400);
//            int hours = (int) ((timeInSeconds % 3600)/3600);
//
//            if (days == 0 & hours ==0){
//                String actualTime = (days +" days," + hours + " hours");
//            }
            Course course = (repo.findById(courseId)).get();

            try {
                assignment = new AssignmentUpload(title,
                        file.getContentType(),
                        file.getBytes(),
                        dateDue,
                        LocalDateTime.now(),
                        course);
                course.getCourse_assignments().add(assignment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return assignment;
    }

    @Override
    public AssignmentUpload getAssignmentUploadById(Long assignmentId) {
        return assignmentRepo.findById(assignmentId).get() ;
    }

    @Override
    public String updateAssignmentUpload(Long assignmentId, String title, MultipartFile file) throws IOException {
        AssignmentUpload assignmentUpload = assignmentRepo.findById(assignmentId).get();
        assignmentUpload.setAssignmentTitle(title);
        assignmentUpload.setDocType(file.getContentType());
        assignmentUpload.setContent(file.getBytes());

        return"Assignment has been updated";

    }

}