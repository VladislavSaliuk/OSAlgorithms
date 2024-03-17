package org.example.algorithms;

import java.util.Scanner;

public class ShortestRemainingTime implements Algorithm {
    private int processNumber;
    private int[] burstTimes;
    private int[] arrivalTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private Scanner scanner;

    public ShortestRemainingTime() {
        scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        processNumber = scanner.nextInt();

        burstTimes = new int[processNumber];
        arrivalTimes = new int[processNumber];
        waitingTimes = new int[processNumber];

        for (int i = 0; i < processNumber; i++) {
            System.out.print("Enter the burst time for Process " + (i + 1) + " : ");
            burstTimes[i] = scanner.nextInt();
        }

        System.out.println();

        for (int i = 0; i < processNumber; i++) {
            System.out.print("Enter the arrival time for Process " + (i + 1) + " : ");
            arrivalTimes[i] = scanner.nextInt();
        }
    }

    @Override
    public void start() {
        int completedProcesses = 0;
        int currentTime = 0;
        int shortestRemainingTime = Integer.MAX_VALUE;
        int shortestProcessIndex = 0;
        int finishTime = 0;
        boolean foundProcess = false;
        int[] remainingBurstTimes = burstTimes.clone();

        while (completedProcesses != processNumber) {
            for (int i = 0; i < processNumber; i++) {
                if ((arrivalTimes[i] <= currentTime) && (remainingBurstTimes[i] < shortestRemainingTime) && (remainingBurstTimes[i] > 0)) {
                    shortestRemainingTime = remainingBurstTimes[i];
                    shortestProcessIndex = i;
                    foundProcess = true;
                }
            }
            if (!foundProcess) {
                currentTime++;
                continue;
            }
            remainingBurstTimes[shortestProcessIndex]--;

            shortestRemainingTime = remainingBurstTimes[shortestProcessIndex];
            if (shortestRemainingTime == 0) {
                shortestRemainingTime = Integer.MAX_VALUE;
            }

            if (remainingBurstTimes[shortestProcessIndex] == 0) {
                completedProcesses++;
                foundProcess = false;
                finishTime = currentTime + 1;

                waitingTimes[shortestProcessIndex] = finishTime - burstTimes[shortestProcessIndex] - arrivalTimes[shortestProcessIndex];

                if (waitingTimes[shortestProcessIndex] < 0) {
                    waitingTimes[shortestProcessIndex] = 0;
                }
            }

            currentTime++;
        }

        turnaroundTimes = new int[processNumber];
        for (int i = 0; i < processNumber; i++) {
            turnaroundTimes[i] = burstTimes[i] + waitingTimes[i];
        }

        System.out.println("Process burst time, waiting time, and turnaround time:");

        System.out.println();

        float totalWait = 0;
        float totalTurnaround = 0;

        turnaroundTimes = new int[processNumber];
        for (int i = 0; i < processNumber; i++) {
            turnaroundTimes[i] = burstTimes[i] + waitingTimes[i];
            System.out.printf("P%d - %10d %10d %10d\n", (i + 1), burstTimes[i], waitingTimes[i], turnaroundTimes[i]);
            totalWait += waitingTimes[i];
            totalTurnaround += turnaroundTimes[i];
        }

        System.out.println("Average waiting time is " + (totalWait / processNumber));
        System.out.println("Average turnaround time is " + (totalTurnaround / processNumber));
    }
}
