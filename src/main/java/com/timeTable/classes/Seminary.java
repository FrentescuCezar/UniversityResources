package com.timeTable.classes;

public class Seminary extends Room{


   public Seminary(int numberOfChalk, int numberOfSponges, int numberOfVideoProjectors, int numberOfComputers) {
        super(numberOfChalk, numberOfSponges, numberOfVideoProjectors, numberOfComputers);
        this.type = TypeOfRoom.SEMINARY;
    }
}
