package com.drolewski.verifier;

import com.drolewski.model.Job;

import java.util.List;

public class Verifier {

    private static int evaluateOutputData(OutputData outputData, List<Job> inputData) {
        int currentTime = 1;
        int lateTaskValue = 0;
        for(Integer i : outputData.getJobOrder()) {
            Job job = inputData.get(i - 1);
            if(currentTime < job.getReadyMoment()){
                currentTime = job.getReadyMoment();
            }
            currentTime += job.getDurationTime();
            if(currentTime > job.getExpectedEndTime()){
                lateTaskValue += job.getWeight();
            }
        }
        return lateTaskValue;
    }

    private static boolean verifySolution(OutputData solution){
        return solution.getJobOrder().stream().distinct().count() == solution.getJobOrder().size();
    }

    public static boolean verifySolution(OutputData solution, List<Job> inputData) {
        int lateTaskValue = evaluateOutputData(solution, inputData);
        System.out.println(lateTaskValue);
        return verifySolution(solution) && solution.getCriteriaValue() == lateTaskValue;
    }
}
