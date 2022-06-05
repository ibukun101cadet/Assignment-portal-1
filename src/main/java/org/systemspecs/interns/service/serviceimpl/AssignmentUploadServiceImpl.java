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
import java.time.LocalDate;

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
    public void uploadAssignment(MultipartFile file,
                                 String assignmentTitle,
                                 String dueDate,
                                 Long courseId) {
        {
            String title = assignmentTitle;
            LocalDate dateDue = LocalDate.parse(dueDate);

            Course course = (repo.findById(courseId)).get();

            try {
                AssignmentUpload assignment = new AssignmentUpload(title,
                        file.getContentType(),
                        file.getBytes(),
                        dateDue,
                        course);
                course.getCourse_assignments().add(assignment);
            }

            catch (IOException e) {
                e.printStackTrace();
            }


    }
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