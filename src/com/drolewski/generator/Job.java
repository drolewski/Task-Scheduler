package com.drolewski.generator;

public class Job {
    private int durationTime;
    private int readyMoment;
    private int expectedEndTime;
    private int weight;

    public Job(int durationTime, int readyMoment, int expectedEndTime, int weight) {
        this.durationTime = durationTime;
        this.readyMoment = readyMoment;
        this.expectedEndTime = expectedEndTime;
        this.weight = weight;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public int getReadyMoment() {
        return readyMoment;
    }

    public int getExpectedEndTime() {
        return expectedEndTime;
    }

    public int getWeight() {
        return weight;
    }

    public String getJobDescription() {
        return this.getDurationTime() +
                " " + this.getReadyMoment() +
                " " + this.getExpectedEndTime() +
                " " + this.getWeight() + "\n";
    }

    @Override
    public String toString() {
        return "Job{" +
                "durationTime=" + durationTime +
                ", readyMoment=" + readyMoment +
                ", expectedEndTime=" + expectedEndTime +
                ", weight=" + weight +
                '}';
    }
}
