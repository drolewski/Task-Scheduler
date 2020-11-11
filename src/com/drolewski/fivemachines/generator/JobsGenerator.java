package com.drolewski.fivemachines.generator;


import com.drolewski.fivemachines.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JobsGenerator {
    private int numberOfJobs;
    private List<Job> jobs;

    public JobsGenerator(int numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
        this.jobs = new ArrayList<>();
    }

    public List<Job> generateListOfJob() {
        var random = new Random();
        for (int i = 0; i < numberOfJobs; ++i) {
            int p = random.nextInt(numberOfJobs / 10) + 1;
            int r = random.nextInt((int) (numberOfJobs * 0.5));
            var job = new Job(p, r);
            this.jobs.add(job);
        }
        return this.jobs;
    }
}
