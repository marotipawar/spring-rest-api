package com.maroti.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.maroti.exception.StudentNotFoundException;
import com.maroti.model.Student;
import com.maroti.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
        Student student=studentService.findStudent(roll);
        if(student==null){
            throw new StudentNotFoundException("ID: "+roll);
        }
        return student;
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

/* HATEOAS */
    @GetMapping(path ={"/students/{roll}"})
    public EntityModel<Student> retrieveUser(@PathVariable Integer roll){

        Student student=studentService.findStudent(roll);

        EntityModel<Student> model = EntityModel.of(student);

        WebMvcLinkBuilder link =linkTo(methodOn(this.getClass()).findAll());
        model.add(link.withRel("/allStudent"));

        return model;
    }

    /* JSON Dynamic Filtering*/
    @GetMapping(path = {"/jsonFilter"})
    public MappingJacksonValue dynamicFilter(){
        Student student = Student.builder().roll(101).name("Maroti").addr("Pune").build();
        MappingJacksonValue mapping = new MappingJacksonValue(student);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("addr","name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("studentFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }
}

