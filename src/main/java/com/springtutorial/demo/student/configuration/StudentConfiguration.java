package com.springtutorial.demo.student.configuration;

import com.springtutorial.demo.student.entity.Student;
import com.springtutorial.demo.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student student1 = new Student(1L,
                    "woldemar",
                    "woldemar@email.com",
                    LocalDate.of(1984, Month.JUNE, 7));

            Student student2 = new Student(2L,
                    "bob",
                    "bob@email.com",
                    LocalDate.of(1999, Month.JUNE, 7));

            repository.saveAll(List.of(student1, student2));
        };
    }
}
