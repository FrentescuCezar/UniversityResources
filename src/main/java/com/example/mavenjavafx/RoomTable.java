package com.example.mavenjavafx;

public class RoomTable {
    String room;
    String type;
    Integer capacity;
    Integer chalks;
    Integer sponges;
    Integer videoprojectors;
    Integer Computers;

    public RoomTable(String room, String type, Integer capacity, Integer chalks, Integer sponges, Integer videoprojectors, Integer computers) {
        this.room = room;
        this.type = type;
        this.capacity = capacity;
        this.chalks = chalks;
        this.sponges = sponges;
        this.videoprojectors = videoprojectors;
        Computers = computers;
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getChalks() {
        return chalks;
    }

    public Integer getSponges() {
        return sponges;
    }

    public Integer getVideoprojectors() {
        return videoprojectors;
    }

    public Integer getComputers() {
        return Computers;
    }
}
