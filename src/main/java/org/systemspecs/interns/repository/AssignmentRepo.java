package org.systemspecs.interns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.systemspecs.interns.domain.Course_assignment;


public interface AssignmentRepo extends JpaRepository<Course_assignment, Long> {


}
