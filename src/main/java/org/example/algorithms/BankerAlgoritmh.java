package org.example.algorithms;

import java.util.Scanner;

public class BankerAlgoritmh implements Algorithm{
    private Scanner scanner;
    private int processesCount;
    private int resourcesCount;
    private int[] availiableVector;
    private boolean[] areDoneProcesses;
    private int[][] allocationMatrix;
    private int[][] maxMatrix;
    private int[][] needMatrix;
    public BankerAlgoritmh() {
        scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        processesCount = scanner.nextInt();

        System.out.print("Enter the number of resources: ");
        resourcesCount = scanner.nextInt();

        availiableVector = new int[resourcesCount];
        maxMatrix = new int[processesCount][resourcesCount];
        allocationMatrix = new int[processesCount][resourcesCount];
        needMatrix = new int[processesCount][resourcesCount];

        System.out.println("Enter the max matrix:");
        for(int i = 0; i < processesCount; i++){
            for(int j = 0; j < resourcesCount; j++){
                System.out.print("For process " + i + " : ");
                maxMatrix[i][j] =  scanner.nextInt();
                System.out.println();
            }
        }

        System.out.println("Enter the allocation matrix:");
        for(int i = 0; i < processesCount; i++){
            for(int j = 0; j < resourcesCount; j++){
                System.out.print("For process " + i + " : ");
                allocationMatrix[i][j] = scanner.nextInt();
                System.out.println();
            }
        }

        System.out.println("Entrer availiable vector:");
        for(int i = 0; i < resourcesCount; i++){
            availiableVector[i] = scanner.nextInt();
            System.out.println();
        }

        needMatrix = calculateNeedMatrix();
        areDoneProcesses = new boolean[processesCount];

    }
    @Override
    public void start() {
        int i = 0;
        boolean isAllocated;
        while (i < processesCount) {
            isAllocated = false;
            for (int j = 0; j < processesCount; j++) {
                if (!areDoneProcesses[j] && checkResources(j)) {
                    for (int k = 0; k < resourcesCount; k++) {
                        availiableVector[k] += allocationMatrix[j][k];
                    }
                    System.out.println("Allocated process " + j);
                    areDoneProcesses[j] = true;
                    isAllocated = true;
                    i++;
                }
            }
            if (!isAllocated) {
                System.err.println("Unable to allocate processes!");
                break;
            }
        }
        if (i == processesCount) {
            System.out.println("Safely allocated!");
        }
    }


    private int[][] calculateNeedMatrix(){
        for(int i = 0; i < processesCount; i++){
            for(int j = 0; j < resourcesCount; j++){
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
        return needMatrix;
    }

    private boolean checkResources(int processNumber){
        for(int i = 0;i < resourcesCount;i++){
            if(availiableVector[i] < needMatrix[processNumber][i]){
                return false;
            }
        }
        return true;
    }

}
