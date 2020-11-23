package com.drolewski.fivemachines.verifier;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.util.List;
import java.util.Map;

public class Verifier {

    public static boolean verifySolution(OutputData outputData, List<Job> jobs, List<Machine> machines) {
        int lateTaskValue = evaluateOutputData(outputData.getScheduledJobs(), jobs, machines);
        System.out.println(lateTaskValue);
        return verifySolution(outputData) && outputData.getCriteriaValue() == lateTaskValue;
    }

    public static int evaluateOutputData(Map<Integer, List<Integer>> scheduledJobs, List<Job> jobs, List<Machine> machines) {
        int lateTaskValue = 1;
        for (Integer jobsInOrder : scheduledJobs.keySet()) {
            int currentTime = 1;
            Machine machine = machines.get(jobsInOrder);
            for (Integer jobFromOrder : scheduledJobs.get(jobsInOrder)) {
                Job job = jobs.get(jobFromOrder - 1);
                if (currentTime < job.getReadyMoment()) {
                    currentTime = job.getReadyMoment();
                }
                currentTime += job.getDurationTime() / machine.getSpeed();
                lateTaskValue += (currentTime - job.getReadyMoment()) / scheduledJobs.keySet().size();
            }
        }
        return lateTaskValue;
    }

    private static boolean verifySolution(OutputData outputData) {
        int instanceSize = 0;
        int distinctSize = 0;
        for (List<Integer> jobs : outputData.getScheduledJobs().values()) {
            instanceSize += jobs.size();
            distinctSize += jobs.stream().distinct().count();
        }
        return instanceSize == distinctSize;
    }
}
