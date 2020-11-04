package com.drolewski.algorithm.approximation;

import com.drolewski.model.Job;
import com.drolewski.model.JobWithRatio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SingleProcessorApproximation {
    private List<JobWithRatio> jobsWithRatio;

    public SingleProcessorApproximation() {
        this.jobsWithRatio = new ArrayList<>();
    }

    public List<Integer> approximationAlgorithm(List<Job> jobs) {
        List<Job> jobsCopy = new ArrayList<>(jobs);
        jobs.sort(Comparator.comparingInt(Job::getReadyMoment));
        for (Job job : jobs) {
            this.jobsWithRatio.add(new JobWithRatio(job, calculateScheduleRatio(job)));
        }
        List<Job> result = new ArrayList<>();
        List<JobWithRatio> sameReadyMoment = new ArrayList<>();
        for (JobWithRatio job : jobsWithRatio) {
            if (sameReadyMoment.size() == 0) {
                sameReadyMoment.add(job);
            } else {
                if (sameReadyMoment.get(0).getJob().getReadyMoment() == job.getJob().getReadyMoment()) {
                    sameReadyMoment.add(job);
                } else if (sameReadyMoment.get(0).getJob().getReadyMoment() != job.getJob().getReadyMoment()) {
                    this.scheduleTasks(sameReadyMoment);
                    for (JobWithRatio jobWithRatio : sameReadyMoment) {
                        result.add(jobWithRatio.getJob());
                    }
                    sameReadyMoment = new ArrayList<>();
                    sameReadyMoment.add(job);
                }
            }
        }
        this.scheduleTasks(sameReadyMoment);
        for (JobWithRatio jobWithRatio : sameReadyMoment) {
            result.add(jobWithRatio.getJob());
        }
        return algorithmResult(jobsCopy, result);
    }

    private List<Integer> algorithmResult(List<Job> jobsCopy, List<Job> jobs) {
        List<Integer> resultSchedule = new ArrayList<>();
        for (Job job : jobs) {
            int index = jobsCopy.indexOf(job) + 1;
            resultSchedule.add(index);
        }
        return resultSchedule;
    }

    private double calculateScheduleRatio(Job job) {
        if (job.getWeight() == 0) {
            return 0.1 * (job.getExpectedEndTime() - (job.getDurationTime() + job.getReadyMoment()));
        }
        return job.getWeight() * (job.getExpectedEndTime() - (job.getDurationTime() + job.getReadyMoment()));
    }

    private void scheduleTasks(List<JobWithRatio> jobs) {
        jobs.sort((a, b) -> Double.compare(b.getRatio(), a.getRatio()));
    }
}
