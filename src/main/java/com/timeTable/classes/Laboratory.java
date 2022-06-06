package com.timeTable.classes;


public class Laboratory extends Room{


    public Laboratory(int numberOfChalk, int numberOfSponges, int numberOfVideoProjectors, int numberOfComputers) {
        super(numberOfChalk, numberOfSponges, numberOfVideoProjectors, numberOfComputers);
        this.type = TypeOfRoom.LABORATORY;
    }
}
