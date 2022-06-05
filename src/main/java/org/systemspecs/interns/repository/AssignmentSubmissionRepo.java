package org.systemspecs.interns.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.AssignmentUpload;

import java.util.List;


public interface AssignmentSubmissionRepo extends JpaRepository<AssignmentSubmission, Long> {



    @Query("SELECT a FROM AssignmentSubmission a WHERE a.assignmentUpload =?1")
    List<AssignmentSubmission> findByAssignmentUpload(AssignmentUpload assignmentUpload);



}
