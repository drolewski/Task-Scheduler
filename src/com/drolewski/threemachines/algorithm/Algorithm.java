package com.drolewski.threemachines.algorithm;

import com.drolewski.threemachines.model.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithm {

    public List<Integer> algorithm(List<Job> jobs) {
        List<Job> result = jobs.stream().sorted(
                                Comparator.comparingDouble(Job::getValue))
        .collect(Collectors.toList());
        return generateResult(jobs, result);
    }

    private List<Integer> generateResult(List<Job> jobs, List<Job> result) {
        List<Integer> res = new ArrayList<>();
        for(Job job: result){
            res.add(jobs.indexOf(job) + 1);
        }
        return res;
    }


}
