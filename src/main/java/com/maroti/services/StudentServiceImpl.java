package com.maroti.services;

import com.maroti.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class StudentServiceImpl implements StudentService{
    private static List<Student> studentList;

    public StudentServiceImpl() {

    }

    static {
        studentList = new ArrayList<>();
    }


    @Override
    public Student findStudent(Integer roll) {
        Predicate<? super Student> predicate = std -> std.getRoll() == roll;
        return studentList.stream().filter(predicate).findFirst().orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void addStudent(Student std) {
        studentList.add(std);
    }

    @Override
    public Student updateStudent(Student std) {
        Student student=studentList.stream().filter(st->st.getRoll() == std.getRoll()).findFirst().orElse(null);
        student.setName(std.getName());
        student.setAddr(std.getAddr());
        studentList.add(student);
        return student;
    }

    @Override
    public boolean deleteStudent(Integer roll) {
        Student st=studentList.stream().filter(std->std.getRoll() == roll).findFirst().orElse(null);
        return studentList.remove(st);
    }
}
