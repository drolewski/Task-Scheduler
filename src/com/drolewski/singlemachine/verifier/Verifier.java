package com.drolewski.singlemachine.verifier;

import com.drolewski.singlemachine.model.Job;

import java.util.List;

public class Verifier {

    public static int evaluateOutputData(List<Integer> outputData, List<Job> inputData) {
        int currentTime = 1;
        int lateTaskValue = 0;
        for(Integer i : outputData) {
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
        int lateTaskValue = evaluateOutputData(solution.getJobOrder(), inputData);
        System.out.println(lateTaskValue);
        return verifySolution(solution) && solution.getCriteriaValue() == lateTaskValue;
    }
}
