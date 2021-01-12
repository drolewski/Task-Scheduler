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
        double result = 0;
        double sumW = 0;
        for (Integer jobsInOrder : scheduledJobs) {
            Job job = jobs.get(jobsInOrder - 1);
            machineTimes[0] += job.getJobsPerMachine().get(0);
            machineTimes[1] = Math.max(machineTimes[0], machineTimes[1]) + job.getJobsPerMachine().get(1);
            machineTimes[2] = Math.max(machineTimes[2], machineTimes[1]) + job.getJobsPerMachine().get(2);

            result += job.getWeight() * Math.max(0, machineTimes[2] - job.getEndTime());
            sumW += job.getWeight();
        }
        return result / sumW;
    }

    private static boolean verifySolution(OutputData outputData) {
        return outputData.getScheduledJobs().stream().distinct().count() == outputData.getScheduledJobs().size();
    }
}
