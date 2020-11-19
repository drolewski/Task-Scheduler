package com.drolewski.fivemachines.main;

import com.drolewski.fivemachines.algorithm.Algorithm;
import com.drolewski.fivemachines.generator.Generator;
import com.drolewski.fivemachines.verifier.InputData;
import com.drolewski.fivemachines.verifier.OutputData;

import java.util.List;
import java.util.Map;

import static com.drolewski.fivemachines.verifier.InstanceReader.*;
import static com.drolewski.fivemachines.verifier.Verifier.verifySolution;

public class Main {
    public static void main(String[] args) {
        // generate instances
//        Generator.generateAllInstances();

        // Read input instances
        List<InputData> jobsList = readInputData("src/com/drolewski/fivemachines/generator/generatedData");

        // Algorithm
//        for(InputData inputData : jobsList){
//            Algorithm algo = new Algorithm();
//            Map<Integer, List<Integer>> scheduledJobs = algo.algorithm(inputData);
//            // czasy
//            saveOutputFile(scheduledJobs, inputData);
//        }

        // Read output
        List<OutputData> outputData = readOutputData("src/com/drolewski/fivemachines/generator/generatedData");

        // Validate
        for (int i = 0; i < outputData.size(); ++i) {
            System.out.println(outputData.get(i).getFileName());
            boolean result = verifySolution(outputData.get(i), jobsList.get(i).getJobs(), jobsList.get(i).getMachines());
            System.out.println(result + "\n");
        }
    }
}
