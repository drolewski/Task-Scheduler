package com.drolewski.main;

import com.drolewski.algorithm.approximation.SingleProcessorApproximation;
import com.drolewski.generator.Generator;
import com.drolewski.model.Job;
import com.drolewski.verifier.OutputData;

import java.util.List;

import static com.drolewski.generator.FileGenerator.saveOutputDataFile;
import static com.drolewski.verifier.FileDataReader.readInputData;
import static com.drolewski.verifier.FileDataReader.readOutputData;
import static com.drolewski.verifier.Verifier.verifySolution;

public class Main {
    public static void main(String[] args) {
        // read data from input - all files
        List<List<Job>> jobsList = readInputData("src/com/drolewski/generator/generatedData");

        // here is place to run algorithm on input data in verifierDataList
        for(List<Job> jobs : jobsList) {
            SingleProcessorApproximation approximation = new SingleProcessorApproximation();
            List<Integer> scheduledJobs = approximation.approximationAlgorithm(jobs);
            saveOutputDataFile(scheduledJobs, jobs);
        }

        // read data from algorithm output
        List<OutputData> outputData = readOutputData("src/com/drolewski/generator/generatedData");

        // validate solution
        for (int i = 0; i < outputData.size(); ++i) {
            System.out.println(outputData.get(i).getFileName());
            boolean result = verifySolution(outputData.get(i), jobsList.get(i));
            System.out.println(result + "\n");
        }
    }
}
