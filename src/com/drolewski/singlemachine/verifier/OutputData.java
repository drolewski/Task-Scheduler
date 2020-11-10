package com.drolewski.singlemachine.verifier;

import java.util.List;

public class OutputData {
    private String fileName;
    private List<Integer> jobOrder;
    private int criteriaValue;

    public OutputData(List<Integer> jobOrder, int criteriaValue, String fileName) {
        this.jobOrder = jobOrder;
        this.criteriaValue = criteriaValue;
        this.fileName = fileName;
    }

    public List<Integer> getJobOrder() {
        return jobOrder;
    }

    public int getCriteriaValue() {
        return criteriaValue;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "jobOrder=" + jobOrder +
                ", criteriaValue=" + criteriaValue +
                '}';
    }
}
