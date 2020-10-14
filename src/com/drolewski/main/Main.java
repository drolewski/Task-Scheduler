package com.drolewski.main;

import com.drolewski.generator.Job;
import com.drolewski.verifier.OutputData;

import java.util.List;

import static com.drolewski.verifier.FileDataReader.readInputData;
import static com.drolewski.verifier.FileDataReader.readOutputData;
import static com.drolewski.verifier.Verifier.verifySolution;

public class Main {
    public static void main(String[] args) {
        // read data from input
        List<List<Job>> jobsList = readInputData("src/com/drolewski/generator/generatedData");

        // here is place to run algorithm on input data in verifierDataList

        // read data from algorithm output
        List<OutputData> outputData = readOutputData("src/com/drolewski/generator/generatedData");

        // validate solution
        for (int i = 0; i < outputData.size(); ++i) {
            boolean result = verifySolution(outputData.get(i), jobsList.get(i));
            System.out.println(result);
        }
    }
}
