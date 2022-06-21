package org.systemspecs.interns.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.systemspecs.interns.domain.AppUser;
import org.systemspecs.interns.domain.Course;

import java.util.List;
//how come this is not annotated as service?
public interface AppUserService extends UserDetailsService {

    void save(AppUser user);

    AppUser getUser(String fullname);

    List<Course> getAllUserCourses(Long id);

    UserDetails loadUserByUsername(String email);




}
