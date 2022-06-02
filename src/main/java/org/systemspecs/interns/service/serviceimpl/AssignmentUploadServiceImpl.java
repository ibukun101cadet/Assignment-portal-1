package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.repository.AssignmentUploadRepo;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.service.AssignmentUploadService;

import java.io.IOException;

@Service

@Transactional

public class AssignmentUploadServiceImpl implements AssignmentUploadService {

    private final CourseRepo repo;
    private final AssignmentUploadRepo assignmentRepo;



    public AssignmentUploadServiceImpl(CourseRepo repo, AssignmentUploadRepo assignmentRepo) {
        this.repo = repo;
        this.assignmentRepo = assignmentRepo;
    }

    @Override
    public void saveFile(MultipartFile file, Long courseId) {
        {
            String docname = StringUtils.cleanPath(file.getOriginalFilename());
            Course course = (repo.findById(courseId)).get();

            try {
                AssignmentUpload assignment = new AssignmentUpload(docname, file.getContentType(), file.getBytes(), course);
                course.getCourse_assignments().add(assignment);
            }

            catch (IOException e) {
                e.printStackTrace();
            }


    }
}

    @Override
    public AssignmentUpload getById(Long assignmentId) {
        return assignmentRepo.findById(assignmentId).get() ;
    }

    @Override
    public String updateAssignment(Long assignmentId, String docName, MultipartFile file) throws IOException {
        AssignmentUpload assignmentUpload = assignmentRepo.findById(assignmentId).get();
        assignmentUpload.setDocName(docName);
        assignmentUpload.setDocType(file.getContentType());
        assignmentUpload.setContent(file.getBytes());

        return"Assignment has been updated";

    }

}