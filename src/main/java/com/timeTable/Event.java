package com.timeTable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timeTable.classes.Room;

import java.util.List;

public class Event{
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String nameOfDiscipline;
    private String type;
    private List<String> teacher;
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

    public String getNameOfDiscipline() {
        return nameOfDiscipline;
    }

    public String getType() {
        return type;
    }

    public List<String> getTeacher() {
        return teacher;
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

    public void setNameOfDiscipline(String nameOfDiscipline) {
        this.nameOfDiscipline = nameOfDiscipline;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTeacher(List<String> teacher) {this.teacher = teacher;}

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Event{" +
                "dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", nameOfDiscipline='" + nameOfDiscipline + '\'' +
                ", type='" + type + '\'' +
                ", teacher='" + teacher + '\'' +
                ", room=" + room +
                '}';
    }
}
