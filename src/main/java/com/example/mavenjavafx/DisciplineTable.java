package com.example.mavenjavafx;

public class DisciplineTable {
    String Discipline;
    String Teacher;

    public DisciplineTable(String Discipline, String Teacher) {
        this.Discipline = Discipline;
        this.Teacher = Teacher;
    }

    public String getDiscipline() {
        return Discipline;
    }

    public String getTeacher() {
        return Teacher;
    }
}
