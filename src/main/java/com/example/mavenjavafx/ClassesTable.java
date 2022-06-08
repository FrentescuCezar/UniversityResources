package com.example.mavenjavafx;

public class ClassesTable {
    String Room;
    String Type;
    Integer Capacity;
    Integer Chalks;
    Integer Sponges;
    Integer Videoprojectors;
    Integer Computers;

    public ClassesTable(String room, String type, Integer capacity, Integer chalks, Integer sponges, Integer videoprojectors, Integer computers) {
        Room = room;
        Type = type;
        Capacity = capacity;
        Chalks = chalks;
        Sponges = sponges;
        Videoprojectors = videoprojectors;
        Computers = computers;
    }

    public String getRoom() {
        return Room;
    }

    public String getType() {
        return Type;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public Integer getChalks() {
        return Chalks;
    }

    public Integer getSponges() {
        return Sponges;
    }

    public Integer getVideoprojectors() {
        return Videoprojectors;
    }

    public Integer getComputers() {
        return Computers;
    }
}
