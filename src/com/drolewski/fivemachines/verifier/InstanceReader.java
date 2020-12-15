package com.drolewski.fivemachines.verifier;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.io.*;
import java.util.*;

import static com.drolewski.fivemachines.verifier.Verifier.evaluateOutputData;

public class InstanceReader {
    private static InputData readInputFile(String fileName) {
        List<Job> jobs = new ArrayList<>();
        List<Machine> machines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String machinesLine = reader.readLine();
            String[] machinesString = machinesLine.split("\\s");
            for (String machine : machinesString) {
                machines.add(new Machine(Double.parseDouble(machine)));
            }
            line = reader.readLine();
            while (line != null) {
                String[] jobParameters = line.split("\\s");
                if (jobParameters.length == 2) {
                    var job = new Job(
                            Integer.parseInt(jobParameters[0]),
                            Integer.parseInt(jobParameters[1]));
                    jobs.add(job);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new InputData(jobs, machines, fileName);
    }

    private static OutputData readOutputFile(String fileName) {
        Map<Integer, List<Integer>> jobs = new HashMap<>();
        double algorithmValue = 0;
        int machineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] jobOrder = line.split("\\s");
                if (jobOrder.length > 2) {
                    List<Integer> machineSchedule = new ArrayList<>();
                    for (String task : jobOrder) {
                        machineSchedule.add(Integer.parseInt(task));
                    }
                    jobs.put(machineNumber, machineSchedule);
                    machineNumber++;
                } else {
                    algorithmValue = Double.parseDouble(line);
                }
                line = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new OutputData(jobs, algorithmValue, fileName);
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

    public static void saveOutputFile(Map<Integer, List<Integer>> result, InputData inputData){
        try {
            int jobsSum = 0;
            for(int i = 0; i < 5; i++){
                jobsSum += result.get(i).size();
            }
            FileWriter outputFile = new FileWriter("src/com/drolewski/fivemachines/generator/generatedData/" + "out" + inputData.getFileName().substring(2,9) + jobsSum + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            long value = Math.round(evaluateOutputData(result, inputData.getJobs(), inputData.getMachines()));
            out.write(value + "\n");
            for(int i = 0; i < 5; i++){
                for(Integer res : result.get(i)){
                    out.write(res + " ");
                }
                out.write("\n");
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
