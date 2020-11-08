package com.drolewski.generator;

import com.drolewski.model.Job;
import com.drolewski.verifier.InputData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.drolewski.verifier.Verifier.evaluateOutputData;

public class FileGenerator {
    public static void saveInputFile(List<Job> jobs) {
        try {
            FileWriter inputFile = new FileWriter("src/com/drolewski/generator/generatedData/in136792_" + jobs.size() + ".txt");
            BufferedWriter out = new BufferedWriter(inputFile);
            out.write(jobs.size() + "\n");
            for(Job job : jobs){
                out.write(job.getJobDescription());
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveOutputFile(int instanceSize) {
        try {
            FileWriter outputFile = new FileWriter("src/com/drolewski/generator/generatedData/out136792_" + instanceSize + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            out.write("0\n");
            for(int i = 1; i <= instanceSize; ++i){
                out.write(i + " ");
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveOutputDataFile(List<Integer> jobsSchedule, InputData inputData){
        try {
            FileWriter outputFile = new FileWriter("src/com/drolewski/generator/generatedData/" + "out" + inputData.getFileName().substring(2,9) + jobsSchedule.size() + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            int value = evaluateOutputData(jobsSchedule, inputData.getJobs());
            out.write(value + "\n");
            for(Integer i : jobsSchedule){
                out.write(i + " ");
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateDataFiles(List<Job> jobs) {
        saveInputFile(jobs);
        saveOutputFile(jobs.size());
    }
}
