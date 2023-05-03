package com.maroti.controller;

import com.maroti.dao.StudentDao;
import com.maroti.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentJpaController {
    private StudentDao studentDao;

    @Autowired
    public StudentJpaController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping("/jpa/students")
    public List<Student> findAll(){
        return studentDao.findAll();
    }
}
