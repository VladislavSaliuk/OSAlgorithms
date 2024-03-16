package org.example;

import org.example.algorithms.Algorithm;
import org.example.algorithms.RoundRobin;

import java.util.Scanner;

public class AlgorithmOperator implements Algorithm {

    private RoundRobin roundRobin;

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
                    System.out.println("SRT algorithm");
                    break;
                case 3:
                    System.out.println("BANKER algorithm");
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
