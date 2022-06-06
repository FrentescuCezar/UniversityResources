package com.example.mavenjavafx;

public class Timetable {
    public Timetable(String timetable, String day, String start, String end, String discipline, String type, String teacher, String room, Integer Capacity) {
        Timetable = timetable;
        Day = day;
        Start = start;
        End = end;
        Discipline = discipline;
        Type = type;
        Teacher = teacher;
        Room = room;
        this.Capacity = Capacity;
    }

    String Timetable;
    String Day;
    String Start;
    String End;
    String Discipline;
    String Type;
    String Teacher;
    String Room;
    Integer Capacity;

    public String getTimetable() {
        return Timetable;
    }

    public String getDay() {
        return Day;
    }

    public String getStart() {
        return Start;
    }

    public String getEnd() {
        return End;
    }

    public String getDiscipline() {
        return Discipline;
    }

    public String getType() {
        return Type;
    }

    public String getTeacher() {
        return Teacher;
    }

    public String getRoom() {
        return Room;
    }

    public Integer getCapacity() {
        return Capacity;
    }
}
