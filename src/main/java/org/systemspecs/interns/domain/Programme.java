//package org.systemspecs.interns.domain;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@Entity
//@Getter
//@Setter
////@EqualsAndHashCode//?
//@NoArgsConstructor
//public class Programme {
//    @SequenceGenerator(
//            name = "programme_sequence",
//            sequenceName = "programme_sequence",
//            allocationSize = 1
//    )
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "programme_sequence"
//    )
//    private Long programmeId;
//    @Column(nullable = false)
//    private String programme_title;
//    @Column(nullable = false)
//    private int level;
//
////    @ManyToMany
////    @JoinTable(
////            name = "course_program_mapping",
////            joinColumns = @JoinColumn( nullable = false,
////                    name="programme_id",
////                    referencedColumnName = "programmeId"
////            ),
////            inverseJoinColumns = @JoinColumn(nullable = false,
////                    name ="course_id",
////                    referencedColumnName = "courseId"
////            )
////    )
////    private List<Course> courses;
//
//}
