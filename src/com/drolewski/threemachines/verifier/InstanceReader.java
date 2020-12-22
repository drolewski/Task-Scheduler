package com.drolewski.threemachines.verifier;

import com.drolewski.threemachines.model.Job;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.drolewski.threemachines.verifier.Verifier.evaluateOutputData;


public class InstanceReader {
    private static InputData readInputFile(String fileName) {
        List<Job> jobs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] jobParameters = line.split("\\s");
                if (jobParameters.length == 5) {
                    var jobPerMachine = new ArrayList<Integer>();
                    jobPerMachine.add(Integer.parseInt(jobParameters[0]));
                    jobPerMachine.add(Integer.parseInt(jobParameters[1]));
                    jobPerMachine.add(Integer.parseInt(jobParameters[2]));
                    var job = new Job(jobPerMachine,
                            Integer.parseInt(jobParameters[3]),
                            Integer.parseInt(jobParameters[4]));
                    jobs.add(job);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new InputData(jobs, fileName);
    }

    private static OutputData readOutputFile(String fileName) {
        int algorithmValue = 0;
        var resultJobs = new ArrayList<Integer>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            algorithmValue = Integer.parseInt(line);
            line = reader.readLine();
            String[] jobParameters = line.split("\\s");
            for (String job : jobParameters) {
                resultJobs.add(Integer.parseInt(job));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new OutputData(algorithmValue, resultJobs, fileName);
    }

    public static List<InputData> readInputData(String directoryPath) {
        var directory = new File(directoryPath);
        List<InputData> jobDataList = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().matches("^in[0-9]{6}_[0-9]+\\.txt$")) {
                InputData inputData = InstanceReader.readInputFile(file.getPath());
                inputData.setFileName(file.getName());
                jobDataList.add(inputData);
            }
        }
        return jobDataList;
    }

    public static List<OutputData> readOutputData(String directoryPath) {
        var directory = new File(directoryPath);
        List<OutputData> orderedTaskList = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().matches("^out[0-9]{6}_[0-9]+\\.txt$")) {
                OutputData outputTaskList = InstanceReader.readOutputFile(file.getPath());
                orderedTaskList.add(outputTaskList);
            }
        }
        return orderedTaskList;
    }

    public static void saveOutputFile(List<Integer> result, InputData inputData){
        try {
            int jobsSum = 0;
            FileWriter outputFile = new FileWriter("src/com/drolewski/fivemachines/generator/generatedData/" + "out" + inputData.getFileName().substring(2,9) + jobsSum + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            long value = Math.round(evaluateOutputData(result, inputData.getJobs()));
            out.write(value + "\n");
            for(Integer i : result){
                out.write(i + " ");
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
