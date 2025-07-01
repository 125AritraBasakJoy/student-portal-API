package com.example.studentportal.service;

import com.example.studentportal.model.Student;
import com.example.studentportal.model.StudentRequestDTO;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student updatedStudent);
    void deleteStudent(Long id);
    Student saveStudentFromDTO(StudentRequestDTO studentRequestDTO);
}
