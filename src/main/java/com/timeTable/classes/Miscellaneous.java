package com.timeTable.classes;

public class Miscellaneous {
    private static Miscellaneous single_instance = null;

    private int totalNumberOfChalk;
    private int totalNumberOfSponges;
    private int totalNumberOfVideoProjectors;
    private int totalNumberOfComputers;

    private Miscellaneous()
    {
      this.totalNumberOfChalk = 0;
      this.totalNumberOfSponges = 0;
      this.totalNumberOfVideoProjectors = 0;
      this.totalNumberOfComputers = 0;
    }
    public static Miscellaneous getInstance()
    {
        if (single_instance == null)
            single_instance = new Miscellaneous();

        return single_instance;
    }



}
