package com.drolewski.threemachines.verifier;

import com.drolewski.threemachines.model.Job;

import java.util.List;

public class InputData {
    private List<Job> jobs;
    private String fileName;

    public InputData(List<Job> jobs, String fileName) {
        this.jobs = jobs;
        this.fileName = fileName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "jobs=" + jobs +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
