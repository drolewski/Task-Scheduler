package com.drolewski.fivemachines.algorithm;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;
import com.drolewski.fivemachines.verifier.InputData;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    public Map<Integer, List<Integer>> algorithm(InputData inputData) {
        List<Job> jobs = inputData.getJobs();

        List<Job> jobsSortedByReadyMoment = jobs.stream()
                .sorted(Comparator
                        .comparingInt(Job::getReadyMoment)
                        .thenComparing(Job::getDurationTime)
                )
                .collect(Collectors.toList());

        List<Machine> machines = inputData.getMachines();
        for (Job job : jobsSortedByReadyMoment) {
            int machineIndex = calculateTime(job, machines);
            machines.get(machineIndex).getJobs().add(job);
        }

        Map<Integer, List<Integer>> integerListMap = algorithmResult(machines, jobs, machines);
        System.out.println(integerListMap);
        return integerListMap;
    }

    private int calculateTime(Job job, List<Machine> machines) {
        int bestIndex = 0;
        double bestValue = Double.MAX_VALUE;
        for (Machine machine : machines) {
            double currentValue = 0.0;
            for (Job jobT : machine.getJobs()) {
                if (currentValue < jobT.getReadyMoment()) {
                    currentValue = jobT.getReadyMoment();
                }
                currentValue += jobT.getDurationTime() * (1.0 / machine.getSpeed());
            }
            currentValue += job.getDurationTime() - job.getReadyMoment();
            if (bestValue >= currentValue) {
                bestValue = currentValue;
                bestIndex = machines.indexOf(machine);
            }
        }

        return bestIndex;
    }

    private Map<Integer, List<Integer>> algorithmResult(List<Machine> sortedMachines, List<Job> jobs, List<Machine> machines) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (Machine machine : sortedMachines) {
            List<Integer> indexedJobs = new ArrayList<>();
            for (Job job : machine.getJobs()) {
                indexedJobs.add(jobs.indexOf(job) + 1);
            }
            result.put(machines.indexOf(machine), indexedJobs);
        }
        return result;
    }
}
