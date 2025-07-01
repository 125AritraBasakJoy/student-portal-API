package com.example.studentportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.studentportal.model.Student;
import com.example.studentportal.model.Department;
import com.example.studentportal.model.Program;
import com.example.studentportal.service.StudentService;

@SpringBootApplication
public class StudentPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentPortalApplication.class, args);
    }


}
