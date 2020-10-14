package com.drolewski.verifier;

import com.drolewski.model.Job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileDataReader {
    private static List<Job> readInputFile(String fileName) {
        List<Job> jobs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] jobParameters = line.split("\\s");
                if (jobParameters.length > 2) {
                    var job = new Job(
                            Integer.parseInt(jobParameters[0]),
                            Integer.parseInt(jobParameters[1]),
                            Integer.parseInt(jobParameters[2]),
                            Integer.parseInt(jobParameters[3]));
                    jobs.add(job);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jobs;
    }

    private static OutputData readOutputFile(String fileName) {
        List<Integer> tasks = new ArrayList<>();
        int algorithmValue = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null){
                String[] jobOrder = line.split("\\s");
                if(jobOrder.length > 2) {
                    for(String task : jobOrder){
                        tasks.add(Integer.parseInt(task));
                    }
                } else {
                    algorithmValue = Integer.parseInt(line);
                }
                line = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new OutputData(tasks, algorithmValue);
    }

    public static List<List<Job>> readInputData(String directoryPath) {
        var directory = new File(directoryPath);
        List<List<Job>> jobDataList = new ArrayList<>();
        for(File file : Objects.requireNonNull(directory.listFiles())) {
            if(file.getName().matches("^in[0-9]{6}\\s[0-9]+\\.txt$")){
                List<Job> jobs = FileDataReader.readInputFile(file.getPath());
                jobDataList.add(jobs);
            }
        }
        return jobDataList;
    }

    public static List<OutputData> readOutputData(String directoryPath) {
        var directory = new File(directoryPath);
        List<OutputData> orderedTaskList = new ArrayList<>();
        for(File file : Objects.requireNonNull(directory.listFiles())) {
            if(file.getName().matches("^out[0-9]{6}\\s[0-9]+\\.txt$")){
                OutputData outputTaskList = FileDataReader.readOutputFile(file.getPath());
                orderedTaskList.add(outputTaskList);
            }
        }
        return orderedTaskList;
    }
}
