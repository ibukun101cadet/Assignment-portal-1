//package org.systemspecs.interns.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.domain.Lecturer;
//
//
//import java.util.List;
//
//
//public interface LecturerRepo extends JpaRepository<Course, Long> {
//
//    @Query("SELECT l FROM Lecturer l WHERE l.fullName=?1")
//    Lecturer findByFullName(String fullName);
//
//    @Query("SELECT l.courses_Taught FROM Lecturer l WHERE l.lecturerId=?1")
//    List<Course> findCoursesTaughtById(Long lecturerId);
//
//}
