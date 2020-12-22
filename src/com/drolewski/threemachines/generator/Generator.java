package com.drolewski.threemachines.generator;


import com.drolewski.threemachines.model.Job;

import java.util.List;

import static com.drolewski.fivemachines.generator.MachineGenerator.generateListOfMachines;

public class Generator {
    private final static int[] INSTANCES = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void generateAllInstances() {
        for(int i : INSTANCES){
            var jobsGenerator = new JobsGenerator(i);
            List<Job> jobs = jobsGenerator.generateListOfJob();
            FileGenerator.generateDataFiles(jobs);
        }
    }
}
