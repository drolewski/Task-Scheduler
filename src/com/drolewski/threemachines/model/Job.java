package com.drolewski.threemachines.model;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private List<Integer> jobsPerMachine;
    private int jobsSum;
    private int endTime;
    private int weight;

    public Job(int endTime, int weight) {
        this.jobsPerMachine = new ArrayList<>();
        this.endTime = endTime;
        this.weight = weight;
    }

    public Job(List<Integer> jobs, int endTime, int weight) {
        this.jobsPerMachine = jobs;
        this.endTime = endTime;
        this.weight = weight;
    }

    public Job(List<Integer> jobs, int endTime, int weight, int jobsSum) {
        this.jobsPerMachine = jobs;
        this.endTime = endTime;
        this.weight = weight;
        this.jobsSum = jobsSum;
    }

    public List<Integer> getJobsPerMachine() {
        return jobsPerMachine;
    }

    public void setJobsPerMachine(List<Integer> jobsPerMachine) {
        this.jobsPerMachine = jobsPerMachine;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getJobsSum() {
        return jobsSum;
    }

    public void setJobsSum(int jobsSum) {
        this.jobsSum = jobsSum;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobsPerMachine=" + jobsPerMachine +
                ", jobsSum=" + jobsSum +
                ", endTime=" + endTime +
                ", weight=" + weight +
                '}';
    }

    public String getJobDescription() {
        var resultString = new StringBuilder();
        for (Integer job: this.jobsPerMachine){
            resultString.append(job);
            resultString.append(" ");
        }
        resultString.append(endTime);
        resultString.append(" ");
        resultString.append(weight);
        resultString.append("\n");
        return resultString.toString();
    }

    public double getValue() {
        return this.jobsSum / (double) this.weight;
    }
}
