package com.drolewski.threemachines.verifier;

import java.util.List;

public class OutputData {
    private int criteria;
    private List<Integer> scheduledJobs;
    private String fileName;

    public OutputData(int criteria, List<Integer> scheduledJobs, String fileName) {
        this.criteria = criteria;
        this.scheduledJobs = scheduledJobs;
        this.fileName = fileName;
    }

    public int getCriteria() {
        return criteria;
    }

    public void setCriteria(int criteria) {
        this.criteria = criteria;
    }

    public List<Integer> getScheduledJobs() {
        return scheduledJobs;
    }

    public void setScheduledJobs(List<Integer> scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "jobsNumber=" + criteria +
                ", scheduledJobs=" + scheduledJobs +
                '}';
    }
}
