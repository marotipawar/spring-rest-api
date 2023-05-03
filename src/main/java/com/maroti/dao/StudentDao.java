package com.maroti.dao;

import com.maroti.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StudentDao extends JpaRepository<Student, Integer> {
}
