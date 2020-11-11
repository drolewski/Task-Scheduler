package com.drolewski.fivemachines.model;

public class Job {
    private int durationTime;
    private int readyMoment;

    public Job(int durationTime, int readyMoment) {
        this.durationTime = durationTime;
        this.readyMoment = readyMoment;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getReadyMoment() {
        return readyMoment;
    }

    public void setReadyMoment(int readyMoment) {
        this.readyMoment = readyMoment;
    }


    public String getJobDescription() {
        return this.getDurationTime() +
                " " + this.getReadyMoment() + "\n";
    }
}
