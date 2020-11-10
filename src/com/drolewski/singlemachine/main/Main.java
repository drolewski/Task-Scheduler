package com.drolewski.singlemachine.main;

import com.drolewski.singlemachine.algorithm.approximation.SingleProcessorApproximation;
import com.drolewski.singlemachine.verifier.InputData;
import com.drolewski.singlemachine.verifier.OutputData;

import java.util.List;

import static com.drolewski.singlemachine.generator.FileGenerator.saveOutputDataFile;
import static com.drolewski.singlemachine.verifier.FileDataReader.readInputData;
import static com.drolewski.singlemachine.verifier.FileDataReader.readOutputData;
import static com.drolewski.singlemachine.verifier.Verifier.verifySolution;

public class Main {
    public static void main(String[] args) {
        // read data from input - all files
        List<InputData> jobsList = readInputData("src/com/drolewski/generator/generatedData");

        // here is place to run algorithm on input data in verifierDataList
        for(InputData inputData : jobsList) {
            SingleProcessorApproximation approximation = new SingleProcessorApproximation();
            long start = System.currentTimeMillis();
            List<Integer> scheduledJobs = approximation.approximationAlgorithm(inputData.getJobs());
            long finish = System.currentTimeMillis();
            System.out.println(inputData.getFileName() + " : " + (finish - start));
            saveOutputDataFile(scheduledJobs, inputData);
        }

        // read data from algorithm output
        List<OutputData> outputData = readOutputData("src/com/drolewski/generator/generatedData");

        // validate solution
        for (int i = 0; i < outputData.size(); ++i) {
            System.out.println(outputData.get(i).getFileName());
            boolean result = verifySolution(outputData.get(i), jobsList.get(i).getJobs());
            System.out.println(result + "\n");
        }
    }
}
