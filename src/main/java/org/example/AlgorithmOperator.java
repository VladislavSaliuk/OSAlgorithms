package org.example;

import org.example.algorithms.Algorithm;
import org.example.algorithms.BankerAlgoritmh;
import org.example.algorithms.RoundRobin;
import org.example.algorithms.ShortestRemainingTime;

import java.util.Scanner;

public class AlgorithmOperator implements Algorithm {
    private RoundRobin roundRobin;
    private ShortestRemainingTime shortestRemainingTime;
    private BankerAlgoritmh bankerAlgoritmh;
    @Override
    public void start(){
        int choiseNumber;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the choice");
            System.out.println("1) Round Robin 2) SRT 3)BANKER 4)STRAUSS 5) Exit");
            choiseNumber = scanner.nextInt();
            switch (choiseNumber) {
                case 1:
                    System.out.println("Round Robin scheduling algorithm");
                    roundRobin = new RoundRobin();
                    roundRobin.start();
                    break;
                case 2:
                    System.out.println("Shortest remaining time algorithm");
                    shortestRemainingTime = new ShortestRemainingTime();
                    shortestRemainingTime.start();
                    break;
                case 3:
                    System.out.println("BANKER algorithm");
                    bankerAlgoritmh = new BankerAlgoritmh();
                    bankerAlgoritmh.start();
                    break;
                case 4:
                    System.out.println("STRAUSS algorithm");
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Wrong choise!");
            }
        } while (choiseNumber != 5);
    }

}
