package com.example.mavenjavafx;

public class MiscellaneousTable {
    Integer chalk;
    Integer sponge;
    Integer videoprojector;
    Integer computer;

    public MiscellaneousTable(Integer chalk, Integer sponge, Integer videoprojector, Integer computer) {
        this.chalk = chalk;
        this.sponge = sponge;
        this.videoprojector = videoprojector;
        this.computer = computer;
    }

    public Integer getChalk() {
        return chalk;
    }

    public Integer getSponge() {
        return sponge;
    }

    public Integer getVideoprojector() {
        return videoprojector;
    }

    public Integer getComputer() {
        return computer;
    }
}
