package org.systemspecs.interns.service.serviceimpl;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.systemspecs.interns.domain.AppUser;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Role;
import org.systemspecs.interns.repository.AppUserRepo;
import org.systemspecs.interns.service.AppUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {

    private final AppUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImplementation(AppUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail((email));
        if(user == null){
            System.out.println("User not found in database");
            throw new UsernameNotFoundException("User not found in database");

        }
        else{
            System.out.println("User found in database: {} " + email);


        }
        Collection<SimpleGrantedAuthority>authorities = new ArrayList<>();
        Role role = user.getRole();
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void save(AppUser user) {
        System.out.println("Saving new user {} to the database" + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }




    @Override
    public AppUser getUser(String fullname) {
        System.out.println("Fetching user " + fullname);
        return userRepo.findByFullname(fullname);
    }

    @Override
    public List<Course> getAllUserCourses(Long id) {
        return userRepo.findCoursesById(id);
    }


}
