package com.drolewski.singlemachine.algorithm.approximation;

import com.drolewski.singlemachine.model.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SingleProcessorApproximation {

    public List<Integer> approximationAlgorithm(List<Job> jobs) {
        List<Job> jobsCopy = new ArrayList<>(jobs);
        List<Job> result;
        result = jobs.stream().sorted(
                Comparator
                        .comparingDouble(Job::getExpectedEndTime).thenComparing(Comparator.comparingDouble(Job::calculateTest).reversed())).parallel().collect(Collectors.toList());

        int currentTime = 0;
        List<Job> lastJobs = new ArrayList<>();
        List<Job> jobsResult = new ArrayList<>(result);
        for (Job job : result) {
            if (currentTime < job.getReadyMoment()) {
                currentTime = job.getReadyMoment();
            }
            currentTime += job.getDurationTime();
            if (currentTime > job.getExpectedEndTime()) {
                currentTime -= job.getDurationTime();
                jobsResult.remove(job);
                lastJobs.add(job);
            }
        }
        jobsResult.addAll(lastJobs.stream()
                .sorted(Comparator.comparingInt(Job::getExpectedEndTime).reversed())
                .collect(Collectors.toList())
        );
        return algorithmResult(jobsCopy, jobsResult);
    }

    private List<Integer> algorithmResult(List<Job> jobsCopy, List<Job> jobs) {
        List<Integer> resultSchedule = new ArrayList<>();
        for (Job job : jobs) {
            int index = jobsCopy.indexOf(job) + 1;
            resultSchedule.add(index);
        }
        return resultSchedule;
    }
}
