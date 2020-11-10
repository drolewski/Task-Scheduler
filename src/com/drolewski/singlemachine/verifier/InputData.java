package com.drolewski.singlemachine.verifier;

import com.drolewski.singlemachine.model.Job;

import java.util.List;

public class InputData {
    private String fileName;
    private List<Job> jobs;


    public InputData(String fileName, List<Job> jobs) {
        this.fileName = fileName;
        this.jobs = jobs;
    }

    public String getFileName() {
        return fileName;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
