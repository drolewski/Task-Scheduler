package com.drolewski.fivemachines.generator;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.util.List;

import static com.drolewski.fivemachines.generator.MachineGenerator.generateListOfMachines;

public class Generator {
    private final static int[] INSTANCES = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void generateAllInstances() {
        for(int i : INSTANCES){
            var jobsGenerator = new JobsGenerator(i);
            List<Job> jobs = jobsGenerator.generateListOfJob();
            List<Machine> machines = generateListOfMachines(5);
            FileGenerator.generateDataFiles(jobs, machines);
        }
    }
}
