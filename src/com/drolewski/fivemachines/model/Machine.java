package com.drolewski.fivemachines.model;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private double speed;
    private List<Job> jobs;

    public Machine(double speed) {
        this.speed = speed;
        this.jobs = new ArrayList<>();
    }

    public double getSpeed() {
        return speed;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return Double.toString(this.speed);
    }
}
