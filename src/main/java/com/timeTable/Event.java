package com.timeTable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timeTable.classes.Room;

import java.util.List;

public class Event{
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Discipline discipline;
    private String type;
    private Room room;

    @JsonIgnore
    private String linkToRoom;

    public Event() {
    }

    public Room getRoom() {
        return room;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public String getType() {
        return type;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Event{" +
                "dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", nameOfDiscipline='" + discipline.getName() + '\'' +
                ", type='" + type + '\'' +
                ", teacher='" + discipline.getTeacher() + '\'' +
                ", room=" + room +
                '}';
    }
}
