package org.example.algorithms;

import java.util.Scanner;

public class RoundRobin implements Algorithm {
    private int processNumber;
    private int quantumNumber;
    private int[] burstTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private Scanner scanner;

    public RoundRobin() {
        scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        processNumber = scanner.nextInt();

        burstTimes = new int[processNumber];
        waitingTimes = new int[processNumber];

        for (int i = 0; i < burstTimes.length; i++) {
            System.out.print("Enter the burst time for P" + (i + 1) + " : ");
            burstTimes[i] = scanner.nextInt();
        }

        System.out.print("Enter the quantum number: ");
        quantumNumber = scanner.nextInt();
    }

    @Override
    public void start() {
        int total;
        int[] remainingBurstTimes = burstTimes.clone();
        do {
            for (int i = 0; i < processNumber; i++) {
                if (remainingBurstTimes[i] > 0) {
                    if (remainingBurstTimes[i] > quantumNumber) {
                        for (int j = 0; j < processNumber; j++) {
                            if (j != i && remainingBurstTimes[j] > 0) {
                                waitingTimes[j] += quantumNumber;
                            }
                        }
                        remainingBurstTimes[i] -= quantumNumber;
                    } else {
                        for (int j = 0; j < processNumber; j++) {
                            if (j != i && remainingBurstTimes[j] > 0) {
                                waitingTimes[j] += remainingBurstTimes[i];
                            }
                        }
                        remainingBurstTimes[i] = 0;
                    }
                }
            }

            total = 0;
            for (int remainingTime : remainingBurstTimes) {
                total += remainingTime;
            }
        } while (total != 0);

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
