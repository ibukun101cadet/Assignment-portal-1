//package org.systemspecs.interns.domain;
//
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.List;
//
//
//@Entity
//
//public class Lecturer {
//
//
//    @SequenceGenerator(
//            name = "lecturer_sequence",
//            sequenceName = "lecturer_sequence",
//            allocationSize = 1
//    )
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "lecturer_sequence"
//    )
//    private Long lecturerId;
//
//
//    @OneToOne
//    @JoinColumn(name = "app_user_id")
//    private AppUser appUser;
//
//    @Column(nullable = false)
//    private String fullName;
//
//    @Column(nullable = false)
//    private String staffId;
//
//    @ManyToMany
//    @JoinTable(
//            name = "lecturer_course_mapping",
//            joinColumns = @JoinColumn(nullable = false,
//                    name = "lecturer_id",
//                    referencedColumnName = "lecturerId"
//            ),
//            inverseJoinColumns = @JoinColumn(nullable = false,
//                    name = "course_id",
//                    referencedColumnName = "courseId"
//            )
//    )
//    private List<Course> courses_Taught;
//
//    public Lecturer() {
//    }
//
//    public AppUser getAppUser() {
//        return appUser;
//    }
//
//    public void setAppUser(AppUser appUser) {
//        this.appUser = appUser;
//    }
//
//
//    public List<Course> getCourses_Taught() {
//        return courses_Taught;
//    }
//
//    public void setCourses_Taught(List<Course> courses_Taught) {
//        this.courses_Taught = courses_Taught;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getStaffId() {
//        return staffId;
//    }
//
//    public void setStaffId(String staffId) {
//        this.staffId = staffId;
//    }
//
//
//    public Lecturer(AppUser appUser,
//                    String fullName,
//                    String staffId,
//                    List<Course> courses_Taught) {
//        this.appUser = appUser;
//        this.fullName = fullName;
//        this.staffId = staffId;
//        this.courses_Taught = courses_Taught;
//    }
//}