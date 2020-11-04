package com.drolewski.model;

public class JobWithRatio {
    private Job job;
    private Double ratio;

    public JobWithRatio(Job job, Double ratio) {
        this.job = job;
        this.ratio = ratio;
    }

    public Job getJob() {
        return job;
    }

    public Double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "JobWithRatio{" +
                "job=" + job +
                ", ratio=" + ratio +
                '}';
    }

}
