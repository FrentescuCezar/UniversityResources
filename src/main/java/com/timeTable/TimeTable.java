package com.timeTable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeTable {

    private String nameOfTimeTable;
    public List<Event> listOfEvents;


    public TimeTable() {
        this.nameOfTimeTable = getNameOfTimeTable();
        this.listOfEvents = new ArrayList<>();
    }

    public String getNameOfTimeTable() {
        return nameOfTimeTable;
    }

    public void setNameOfTimeTable(String nameOfTimeTable) {
        this.nameOfTimeTable = nameOfTimeTable;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "nameOfTimeTable='" + nameOfTimeTable + '\'' +
                ", listOfEvents=" + listOfEvents +
                '}';
    }

    public boolean checkNameTimeTableForFilter(TimeTable t) {
        return t.getNameOfTimeTable().equals("Orar Informatica, anul 1")
                || t.getNameOfTimeTable().equals("Orar Informatica, anul 2")
                || t.getNameOfTimeTable().equals("Orar Informatica, anul 3")
                || t.getNameOfTimeTable().equals("Orar Master ingineria sistemelor soft")
                || t.getNameOfTimeTable().equals("Orar Master lingvistica computationala")
                || t.getNameOfTimeTable().equals("Orar Master optimizare computationala")
                || t.getNameOfTimeTable().equals("Orar Master securitatea informatiei")
                || t.getNameOfTimeTable().equals("Orar Master sisteme distribuite");
    }


}
