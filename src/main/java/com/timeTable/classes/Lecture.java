package com.timeTable.classes;

public class Lecture extends Room{


    public Lecture(int numberOfChalk, int numberOfSponges, int numberOfVideoProjectors, int numberOfComputers) {
        super(numberOfChalk, numberOfSponges, numberOfVideoProjectors, numberOfComputers);
        this.type = TypeOfRoom.LECTURE;
    }
}
