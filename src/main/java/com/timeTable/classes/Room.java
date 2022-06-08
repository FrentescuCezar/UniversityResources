package com.timeTable.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public abstract class Room {
    public TypeOfRoom type;
    private String name;
    private int capacity;


    private int numberOfChalk = 0;
    private int numberOfSponges = 0;
    private int numberOfVideoProjectors = 0;
    private int numberOfComputers = 0;


    public Room(int numberOfChalk, int numberOfSponges, int numberOfVideoProjectors, int numberOfComputers) {
        this.numberOfChalk = numberOfChalk;
        this.numberOfSponges = numberOfSponges;
        this.numberOfVideoProjectors = numberOfVideoProjectors;
        this.numberOfComputers = numberOfComputers;
    }

    @JsonIgnore
    private String linkToClass;

    public int getNumberOfChalk() {
        return numberOfChalk;
    }

    public int getNumberOfSponges() {
        return numberOfSponges;
    }

    public int getNumberOfVideoProjectors() {
        return numberOfVideoProjectors;
    }

    public String getLinkToClass() {
        return linkToClass;
    }

    public TypeOfRoom getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setNumberOfChalk(int numberOfChalk) {
        this.numberOfChalk = numberOfChalk;
    }

    public void setNumberOfSponges(int numberOfSponges) {
        this.numberOfSponges = numberOfSponges;
    }

    public void setNumberOfVideoProjectors(int numberOfVideoProjectors) {
        this.numberOfVideoProjectors = numberOfVideoProjectors;
    }

    public void setLinkToClass(String linkToClass) {
        this.linkToClass = linkToClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(TypeOfRoom type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", linkToClass='" + linkToClass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity && numberOfChalk == room.numberOfChalk && numberOfSponges == room.numberOfSponges && numberOfVideoProjectors == room.numberOfVideoProjectors && numberOfComputers == room.numberOfComputers && type == room.type && Objects.equals(name, room.name) && Objects.equals(linkToClass, room.linkToClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, capacity, numberOfChalk, numberOfSponges, numberOfVideoProjectors, numberOfComputers, linkToClass);
    }
}
