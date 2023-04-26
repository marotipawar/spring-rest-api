package com.maroti.services;

import com.maroti.model.Student;

import java.util.List;

public interface StudentService {
    public Student findStudent(Integer roll);
    public List<Student> findAll();
    public void addStudent(Student std);

    public Student updateStudent(Student std);

    public boolean deleteStudent(Integer roll);


}
