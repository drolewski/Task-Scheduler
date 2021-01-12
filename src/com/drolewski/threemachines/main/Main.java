package com.drolewski.threemachines.main;

import com.drolewski.threemachines.algorithm.Algorithm;
import com.drolewski.threemachines.verifier.InputData;
import com.drolewski.threemachines.verifier.OutputData;

import java.util.List;

import static com.drolewski.threemachines.verifier.InstanceReader.*;
import static com.drolewski.threemachines.verifier.Verifier.verifySolution;

public class Main {
    public static void main(String[] args) {
//        Generator.generateAllInstances();

        // Read input instances
        List<InputData> jobsList = readInputData("src/com/drolewski/threemachines/generator/generatedData");

        // Algorithm
//        for (InputData inputData : jobsList) {
//            Algorithm algo = new Algorithm();
//            long start = System.currentTimeMillis();
//            List<Integer> scheduledJobs = algo.algorithm(inputData.getJobs());
//            long finish = System.currentTimeMillis();
//            System.out.println(inputData.getFileName() + " : " + (finish - start));
//            saveOutputFile(scheduledJobs, inputData);
//        }

        // Read output
        List<OutputData> outputData = readOutputData("src/com/drolewski/threemachines/generator/generatedData");

        // Validate
        for (int i = 0; i < outputData.size(); ++i) {
            System.out.println(outputData.get(i).getFileName());
            boolean result = verifySolution(outputData.get(i), jobsList.get(i).getJobs());
            System.out.println(result + "\n");
        }
    }
}
