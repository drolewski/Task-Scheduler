package com.drolewski.fivemachines.algorithm;

import com.drolewski.fivemachines.model.Job;


public class JobWithFlag {
    private Job job;
    private boolean flag;

    public JobWithFlag(Job job) {
        this.job = job;
        this.flag = false;
    }

    public Job getJob() {
        return job;
    }

    public boolean isFlag() {
        return flag;
    }
}
