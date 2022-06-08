package com.timeTable.classes;

public class Miscellaneous {
    private static Miscellaneous single_instance = null;

    private int totalNumberOfChalk;
    private int totalNumberOfSponges;
    private int totalNumberOfVideoProjectors;
    private int totalNumberOfComputers;

    public Miscellaneous() {
        this.totalNumberOfChalk = 1;
        this.totalNumberOfSponges = 1;
        this.totalNumberOfVideoProjectors = 1;
        this.totalNumberOfComputers = 1;
    }

    public void setTotalNumberOfChalk(int totalNumberOfChalk) {
        this.totalNumberOfChalk = totalNumberOfChalk;
    }

    public void setTotalNumberOfSponges(int totalNumberOfSponges) {
        this.totalNumberOfSponges = totalNumberOfSponges;
    }

    public void setTotalNumberOfVideoProjectors(int totalNumberOfVideoProjectors) {
        this.totalNumberOfVideoProjectors = totalNumberOfVideoProjectors;
    }

    public void setTotalNumberOfComputers(int totalNumberOfComputers) {
        this.totalNumberOfComputers = totalNumberOfComputers;
    }

    public int getTotalNumberOfChalk() {
        return totalNumberOfChalk;
    }

    public int getTotalNumberOfSponges() {
        return totalNumberOfSponges;
    }

    public int getTotalNumberOfVideoProjectors() {
        return totalNumberOfVideoProjectors;
    }

    public int getTotalNumberOfComputers() {
        return totalNumberOfComputers;
    }

    public static Miscellaneous getInstance()
    {
        if (single_instance == null)
            single_instance = new Miscellaneous();

        return single_instance;
    }



}
