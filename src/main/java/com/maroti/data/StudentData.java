package com.maroti.data;

import com.maroti.model.Student;
import com.maroti.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Stream;



@Slf4j
@Component
public class StudentData implements CommandLineRunner {

    private StudentService studentService;

    public StudentData(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                Student.builder().roll(101).name("Pallavi").addr("Nanded").build(),
                Student.builder().roll(102).name("Jagruti").addr("Pune").build(),
                Student.builder().roll(103).name("Sonali").addr("Mumbai").build(),
                Student.builder().roll(104).name("Poonam").addr("Pune").build(),
                Student.builder().roll(105).name("Priyanka").addr("Nanded").build()
                ).forEach(student -> {
                    log.info("Studnet: {}", student);
                    studentService.addStudent(student);
        });
    }
}
