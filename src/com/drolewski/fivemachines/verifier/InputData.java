package com.drolewski.fivemachines.verifier;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.util.List;

public class InputData {
    private List<Job> jobs;
    private List<Machine> machines;
    private String fileName;

    public InputData(List<Job> jobs, List<Machine> machines, String fileName) {
        this.jobs = jobs;
        this.machines = machines;
        this.fileName = fileName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
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
                ", machines=" + machines +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
