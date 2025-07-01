package com.example.studentportal.model;

public class StudentRequestDTO {
    private String name;
    private String department;
    private String program;


    public StudentRequestDTO() {}
    public StudentRequestDTO(String name, String department, String program) {
        this.name = name;
        this.department = department;
        this.program = program;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
}

