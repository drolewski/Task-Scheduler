package com.drolewski.threemachines.verifier;

import com.drolewski.threemachines.model.Job;

import java.util.List;

public class Verifier {

    public static boolean verifySolution(OutputData outputData, List<Job> jobs) {
        long lateTaskValue = Math.round(evaluateOutputData(outputData.getScheduledJobs(), jobs));
        System.out.println(lateTaskValue);
        return verifySolution(outputData) && outputData.getCriteria() == lateTaskValue;
    }

    public static double evaluateOutputData(List<Integer> scheduledJobs, List<Job> jobs) {
        var machineTimes = new int[]{0, 0, 0};
        double d = 0.0;
        double w = 0.0;
        for (Integer jobsInOrder : scheduledJobs) {
            int taskTime = 0;
            Job job = jobs.get(jobsInOrder - 1);
            for (int num = 0; num < 3; num++) {
                taskTime += Math.max(machineTimes[num], taskTime);
                taskTime += job.getJobsPerMachine().get(num);
                machineTimes[num] = taskTime;
            }
            d += job.getWeight() * Math.max(0, taskTime - job.getEndTime());
            w += job.getWeight();
        }
        return d / w;
    }

    private static boolean verifySolution(OutputData outputData) {
        return outputData.getScheduledJobs().stream().distinct().count() == outputData.getScheduledJobs().size();
    }
}
