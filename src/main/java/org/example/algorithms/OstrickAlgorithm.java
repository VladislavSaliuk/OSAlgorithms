package org.example.algorithms;

import java.util.*;

public class OstrickAlgorithm implements Algorithm{
    private List<Process> processes;
    private boolean[] isDone;

    public OstrickAlgorithm() {
        Scanner scanner = new Scanner(System.in);
        processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int processCount = scanner.nextInt();

        for (int i = 0; i < processCount; i++) {
            System.out.print("Enter the priority for process " + i + ": ");
            int priority = scanner.nextInt();
            System.out.print("Enter the burst time for process " + i + ": ");
            int burstTime = scanner.nextInt();
            processes.add(new Process(i, priority, burstTime));
        }

        isDone = new boolean[processCount];
    }

    @Override
    public void start() {
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        queue.addAll(processes);

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            System.out.println("Executing process " + currentProcess.getId() + " with priority " + currentProcess.getPriority());
            try {
                Thread.sleep(currentProcess.getBurstTime() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Process " + currentProcess.getId() + " completed.");
            isDone[currentProcess.getId()] = true;
        }

        System.out.println("All processes executed.");
    }
    private static class Process {
        private int id;
        private int priority;
        private int burstTime;

        public Process(int id, int priority, int burstTime) {
            this.id = id;
            this.priority = priority;
            this.burstTime = burstTime;
        }

        public int getId() {
            return id;
        }

        public int getPriority() {
            return priority;
        }

        public int getBurstTime() {
            return burstTime;
        }
    }
}
