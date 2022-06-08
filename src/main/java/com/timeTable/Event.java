package com.timeTable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timeTable.classes.Room;

import java.util.List;
import java.util.Objects;

public class Event {
    private String timeTableName;
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

    public String getTimeTableName() {
        return timeTableName;
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

    public void setTimeTableName(String timeTableName) {
        this.timeTableName = timeTableName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timeTableName='" + timeTableName + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", discipline=" + discipline +
                ", type='" + type + '\'' +
                ", room=" + room +
                ", linkToRoom='" + linkToRoom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(timeTableName, event.timeTableName) && Objects.equals(dayOfWeek, event.dayOfWeek) && Objects.equals(startTime, event.startTime) && Objects.equals(endTime, event.endTime) && Objects.equals(discipline, event.discipline) && Objects.equals(type, event.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeTableName, dayOfWeek, startTime, endTime, discipline, type);
    }
}
