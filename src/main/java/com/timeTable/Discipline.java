package com.timeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Discipline {

    private String name;

    private List<String> teacher;


    public Discipline(String nameOfDiscipline){
        this.name = nameOfDiscipline;
        this.teacher = new ArrayList<>();
    }

    public List<String> getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String discipline) {
        this.name = discipline;
    }

    public void setTeacher(String teacher) {
        this.teacher.add(teacher);
    }
    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }


    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(name, that.name) && Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teacher);
    }
}
