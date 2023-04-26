package com.maroti.controller;

import com.maroti.model.Student;
import com.maroti.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = {"/student/{roll}"})
    public Student findStudent(@PathVariable Integer roll){
        return studentService.findStudent(roll);
    }

    @GetMapping(path = {"/allStudent"})
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PutMapping(path = {"/updateStudent"})
    public Student updateStudent(@RequestBody Student std){
        return studentService.updateStudent(std);
    }

    @DeleteMapping(path = {"/deleteStudent/{roll}"})
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer roll){
        studentService.deleteStudent(roll);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = {"/addStudent"})
    public ResponseEntity<Student> addStudent(@RequestBody Student std){
        studentService.addStudent(std);
        return ResponseEntity.ok().build();
    }
}
