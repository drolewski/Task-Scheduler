package com.drolewski.fivemachines.verifier;

import java.util.List;
import java.util.Map;

public class OutputData {
    private Map<Integer, List<Integer>> scheduledJobs;
    private int criteriaValue;
    private String fileName;

    public OutputData(Map<Integer, List<Integer>> scheduledJobs, int criteriaValue, String fileName) {
        this.scheduledJobs = scheduledJobs;
        this.criteriaValue = criteriaValue;
        this.fileName = fileName;
    }

    public Map<Integer, List<Integer>> getScheduledJobs() {
        return scheduledJobs;
    }

    public void setScheduledJobs(Map<Integer, List<Integer>> scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    public int getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(int criteriaValue) {
        this.criteriaValue = criteriaValue;
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
                "scheduledJobs=" + scheduledJobs +
                ", criteriaValue=" + criteriaValue +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
