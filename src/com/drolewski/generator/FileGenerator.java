package com.drolewski.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGenerator {
    public static void saveInputFile(List<Job> jobs) {
        try {
            FileWriter inputFile = new FileWriter("src/com/drolewski/generator/generatedData/in136792 " + jobs.size() + ".txt");
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
            FileWriter outputFile = new FileWriter("src/com/drolewski/generator/generatedData/out136792 " + instanceSize + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            out.write("0\n");
            for(int i = 1; i <= instanceSize; ++i){
                out.write(i + "\n");
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
