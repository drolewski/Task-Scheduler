package com.drolewski.fivemachines.verifier;

import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        int algorithmValue = 0;
        int machineNumber = 1;
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
                    algorithmValue = Integer.parseInt(line);
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
}
