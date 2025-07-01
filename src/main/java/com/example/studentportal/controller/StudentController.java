package com.example.studentportal.controller;

import com.example.studentportal.model.Department;
import com.example.studentportal.model.Program;
import com.example.studentportal.model.Student;
import com.example.studentportal.model.StudentRequestDTO;
import com.example.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/auto")
    public ResponseEntity<Student> createStudentAuto(@RequestBody(required = false) StudentRequestDTO studentRequestDTO) {
        if (studentRequestDTO == null) {
            studentRequestDTO = new StudentRequestDTO();
        }
        Student savedStudent = studentService.saveStudentFromDTO(studentRequestDTO);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        String name = (studentRequestDTO.getName() == null || studentRequestDTO.getName().isEmpty())
            ? "Student" + System.currentTimeMillis()
            : studentRequestDTO.getName();
        Department department = Department.valueOf(studentRequestDTO.getDepartment());
        Program program = Program.valueOf(studentRequestDTO.getProgram());
        Student updatedStudent = new Student(name, department, program);
        Student savedStudent = studentService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
