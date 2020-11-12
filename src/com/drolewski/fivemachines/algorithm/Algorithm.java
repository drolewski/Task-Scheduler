package com.drolewski.fivemachines.algorithm;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;
import com.drolewski.fivemachines.verifier.InputData;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    public Map<Integer, List<Integer>> algorithm(InputData inputData) {
        List<Machine> machines = inputData.getMachines();
        List<Job> jobs = inputData.getJobs();

        List<Job> jobsSortedByReadyMoment = jobs.stream()
                .sorted(Comparator
                        .comparingInt(Job::getReadyMoment)
                        .thenComparing(Comparator.comparingInt(Job::getDurationTime).reversed()))
                .collect(Collectors.toList());

        List<Machine> sortedMachines = machines.stream()
                .sorted(Comparator.comparingDouble(Machine::getSpeed))
                .collect(Collectors.toList());

//        double speedSum = (double) Math.round(machines.stream().map(Machine::getSpeed).reduce(0.0, Double::sum) * 1000) / 1000;

//        int jobsSum = 0;
        Map<Integer, List<Job>> firstSorted = new HashMap<>();
        for (Machine machine : sortedMachines) {
//            int jobsNumber = (int) Math.round(jobs.size() * machine.getSpeed() / speedSum);
//            jobsSum += jobsNumber;
            List<Job> sortedMachine = new ArrayList<>();
            for (int i = 0; i < jobsSortedByReadyMoment.size(); i++) {
                if (i % 5 == sortedMachines.indexOf(machine)) {
                    sortedMachine.add(jobsSortedByReadyMoment.get(i));
                }
            }
            firstSorted.put(machines.indexOf(machine), sortedMachine);
        }
        // proporcja zadań dla maszyn w zależności od speedSum
        // zadania najdłuższe dostaną maszyny najwolniejsze

        return algorithmResult(firstSorted, jobs);
    }

    private Map<Integer, List<Integer>> algorithmResult(Map<Integer, List<Job>> firstSorted, List<Job> jobs) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> resultJobs = new ArrayList<>();
            for (Job job : firstSorted.get(i)) {
                resultJobs.add(jobs.indexOf(job) + 1);
            }
            result.put(i, resultJobs);
        }
        return result;
    }
}
