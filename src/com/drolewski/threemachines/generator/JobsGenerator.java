package com.drolewski.threemachines.generator;


import com.drolewski.threemachines.model.Job;

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
        int numberOfMachines = 3;
        for (int i = 0; i < numberOfJobs; ++i) {
            var job = new ArrayList<Integer>();
            int sum = 1;
            for(int j = 0; j < numberOfMachines; ++j){
                int jobSum = random.nextInt(20);
                job.add(random.nextInt(20));
                sum += jobSum;
            }
            int d = sum + random.nextInt(sum);
            int w = random.nextInt(numberOfJobs / 10);
            var singleJob = new Job(job, d, w);
            this.jobs.add(singleJob);
        }
        return this.jobs;
    }
}
