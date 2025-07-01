package com.example.studentportal.service;

import com.example.studentportal.model.Department;
import com.example.studentportal.model.Program;
import com.example.studentportal.model.Student;
import com.example.studentportal.model.StudentRequestDTO;
import com.example.studentportal.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Data integrity violation: " + ex.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setDepartment(updatedStudent.getDepartment());
        student.setProgram(updatedStudent.getProgram());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    /**
     * Creates and saves a Student entity from a StudentRequestDTO, auto-generating the name.
     * Handles invalid or missing department/program gracefully.
     * @param studentRequestDTO the DTO containing student info
     * @return the saved Student entity
     */
    public Student saveStudentFromDTO(StudentRequestDTO studentRequestDTO) {
        String randomName = "Student" + System.currentTimeMillis(); // Auto-generate name
        Department department = null;
        Program program = null;
        try {
            if (studentRequestDTO.getDepartment() != null) {
                department = Department.valueOf(studentRequestDTO.getDepartment());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid department: " + studentRequestDTO.getDepartment());
        }
        try {
            if (studentRequestDTO.getProgram() != null) {
                program = Program.valueOf(studentRequestDTO.getProgram());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid program: " + studentRequestDTO.getProgram());
        }
        if (department == null || program == null) {
            throw new RuntimeException("Department and Program must not be null or invalid.");
        }
        Student student = new Student(
            randomName,
            department,
            program
        );
        return saveStudent(student);
    }
}
