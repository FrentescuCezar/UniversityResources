package com.timeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {

    private String nameOfTimeTable;
    public List<Event> listOfEvents;


    public TimeTable(){
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


}
