package org.systemspecs.interns.config;//package org.systemspecs.interns.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.domain.Student;
//import org.systemspecs.interns.repository.CourseRepo;
//import org.systemspecs.interns.repository.StudentRepo;
//
//import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.repository.CourseRepo;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(CourseRepo repository){
        return args -> {

            Course GEC300 = new Course(
                    "GEC300",
                    "SDQQ",
                    3);
            repository.save(GEC300);

        };

}}
