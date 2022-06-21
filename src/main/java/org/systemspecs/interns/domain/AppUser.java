package org.systemspecs.interns.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String uniqueId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "appUser_course_mapping",
            joinColumns = @JoinColumn(nullable = false,
                    name="appUserid",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(nullable = false,
                    name ="course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses;


    public AppUser() {
    }

    public AppUser(Long id, String fullname, String email, String password, String uniqueId, Role role, List<Course> courses) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.uniqueId = uniqueId;
        this.role = role;
        this.courses = courses;
    }

    public AppUser(String fullname, String email, String password, String uniqueId, Role role) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.uniqueId = uniqueId;
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role= role;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }


    public List<Course> getCourses() {
        return courses;
    }


    // standard getters and setters
}