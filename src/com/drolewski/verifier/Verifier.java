package com.drolewski.verifier;

import com.drolewski.generator.Job;

import java.util.List;

public class Verifier {
    private static int lateTaskValue = 0;
    private static int currentTime = 0;

    private static int evaluateOutputData(OutputData outputData, List<Job> inputData) {
        for(Integer i : outputData.getJobOrder()) {
            Job job = inputData.get(i - 1);
            currentTime += job.getReadyMoment() + job.getDurationTime();
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
