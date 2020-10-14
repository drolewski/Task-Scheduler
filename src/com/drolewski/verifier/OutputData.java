package com.drolewski.verifier;

import java.util.List;

public class OutputData {
    private List<Integer> jobOrder;
    private int criteriaValue = 0;

    public OutputData(List<Integer> jobOrder, int criteriaValue) {
        this.jobOrder = jobOrder;
        this.criteriaValue = criteriaValue;
    }

    public List<Integer> getJobOrder() {
        return jobOrder;
    }

    public int getCriteriaValue() {
        return criteriaValue;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "jobOrder=" + jobOrder +
                ", criteriaValue=" + criteriaValue +
                '}';
    }
}
