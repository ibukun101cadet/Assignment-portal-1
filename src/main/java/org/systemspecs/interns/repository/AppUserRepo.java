package org.systemspecs.interns.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.systemspecs.interns.domain.AppUser;
import org.systemspecs.interns.domain.Course;

import java.util.List;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    @Query("SELECT s FROM AppUser s WHERE s.fullname=?1")
    AppUser findByFullname(String fullname);

    @Query("SELECT s FROM AppUser s WHERE s.email=?1")
    AppUser findByEmail(String email);

    @Query("SELECT s.courses FROM AppUser s WHERE s.id=?1")
    List<Course> findCoursesById(Long id);


}
