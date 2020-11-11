package com.drolewski.fivemachines.generator;
import com.drolewski.fivemachines.model.Job;
import com.drolewski.fivemachines.model.Machine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGenerator {
    public static void saveInputFile(List<Job> jobs, List<Machine> machines) {
        try {
            FileWriter inputFile = new FileWriter("src/com/drolewski/fivemachines/generator/generatedData/in136792_" + jobs.size() + ".txt");
            BufferedWriter out = new BufferedWriter(inputFile);
            out.write(jobs.size() + "\n");
            for(Machine machine: machines){
                out.write(machine.getSpeed() + " ");
            }
            out.write("\n");
            for(Job job : jobs){
                out.write(job.getJobDescription());
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveOutputFile(int instanceSize, int machinesNumber) {
        try {
            FileWriter outputFile = new FileWriter("src/com/drolewski/fivemachines/generator/generatedData/out136792_" + instanceSize + ".txt");
            BufferedWriter out = new BufferedWriter(outputFile);
            out.write("0\n");
            for(int i = 1; i <= instanceSize; ++i){
                out.write(i + " ");
                if(i % (instanceSize/machinesNumber) == 0){
                    out.write("\n");
                }
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateDataFiles(List<Job> jobs, List<Machine> machines) {
        saveInputFile(jobs, machines);
        saveOutputFile(jobs.size(), machines.size());
    }
}
