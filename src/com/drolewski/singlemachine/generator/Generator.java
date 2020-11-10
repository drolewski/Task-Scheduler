package com.drolewski.singlemachine.generator;

import com.drolewski.singlemachine.model.Job;

import java.util.List;

public class Generator {
    private final static int[] INSTANCES = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void generateAllInstances() {
        for(int i : INSTANCES){
            var generator = new JobsGenerator(i);
            List<Job> jobs = generator.generateListOfJob();
            FileGenerator.generateDataFiles(jobs);
        }
    }
}
