package com.drolewski.fivemachines.generator;

import com.drolewski.fivemachines.model.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MachineGenerator {

    public static List<Machine> generateListOfMachines(int numberOfMachines) {
        var machine1 = new Machine(1.0);
        var random = new Random();
        int positionOf1 = random.nextInt(5);
        List<Machine> result = new ArrayList<>();
        for (int i = 0; i < numberOfMachines; i++) {
            if (i == positionOf1) {
                result.add(machine1);
            } else {
                var machine = new Machine((double) Math.round(random.nextDouble() * 1000) / 1000);
                result.add(machine);
            }
        }
        return result;
    }
}
