package com.example.mavenjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisciplinesTable {
    String Discipline2;
    String Teacher2;

    public DisciplinesTable(String Discipline, String Teacher) {
        this.Discipline2 = Discipline;
        this.Teacher2 = Teacher;
    }

    public String getDiscipline2() {
        return Discipline2;
    }

    public String getTeacher2() {
        return Teacher2;
    }
}
