package org.systemspecs.interns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.systemspecs.interns.domain.AssignmentUpload;


public interface AssignmentUploadRepo extends JpaRepository<AssignmentUpload, Long> {

}
