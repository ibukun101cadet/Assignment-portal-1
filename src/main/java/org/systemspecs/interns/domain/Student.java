//package org.systemspecs.interns.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@Entity
//
//public class Student {
//
//
//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName = "student_sequence",
//            allocationSize = 1
//    )
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_sequence"
//    )
//    private Long studentId;
//
//    @Column(nullable = false)
//    private String fullName;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String matricNo;
//
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private AppUser user;
//
//    public Student (AppUser user) {
//        this.user = user;}
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @ManyToMany
//    @JoinTable(
//            name = "user_course_mapping",
//            joinColumns = @JoinColumn(nullable = false,
//                    name="appUser_id",
//                    referencedColumnName = "appUserId"
//            ),
//            inverseJoinColumns = @JoinColumn(nullable = false,
//                    name ="course_id",
//                    referencedColumnName = "courseId"
//            )
//    )
//    private List<Course> courses;
//
//    public Student() {
//
//    }
//
//    public AppUser getUser() {
//        return user;
//    }
//
//    public void setUser(AppUser user) {
//        this.user = user;
//    }
//
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getMatricNo() {
//        return matricNo;
//    }
//
//    public void setMatricNo(String matricNo) {
//        this.matricNo = matricNo;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//
//
////    @OneToOne
////            @JoinColumn(nullable = false,
////            name ="programme_title",
////            referencedColumnName = "programme_title"
////    )
////    private Programme programme;
//
//
//
//
////    @OneToOne
////    @JoinColumn(name = "student_id",
////            referencedColumnName = "studentId")
////    private AssignmentSubmission assignmentSubmission;
//
//
//
//
//}