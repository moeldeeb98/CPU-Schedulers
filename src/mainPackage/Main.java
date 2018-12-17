package mainPackage;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

    short choose = 0;
    System.out.println("1- Shortest Job First Algorithm");
    System.out.println("2- RoundRobin Algorithm");
    System.out.println("3- Priority Algorithm");

    Scanner scan = new Scanner(System.in);
    choose = scan.nextShort();

    System.out.print("Number Of Process : ");
    Integer numberOfProcess = scan.nextInt();

    switch(choose){
        case 1:{
            System.out.print("Context Switch Time : ");
            Integer contextSwitch = scan.nextInt();
            Process[] processes = new Process[numberOfProcess];
            for (int i = 0; i < numberOfProcess; i++) {
                    processes[i] = new Process();
                    processes[i].name = "P" + String.valueOf(i + 1);
                    System.out.println("Process Name : " + processes[i].name);
                    System.out.print("Process Arrival Time : ");
                    processes[i].arrivalTime = scan.nextInt();
                    System.out.print("Process Burst Time : ");
                    processes[i].burstTime = scan.nextInt();
                    System.out.print("Process Quantum : ");
                    processes[i].quantum = scan.nextInt();
                    System.out.println("-------------------------");
            }
            ShortestJobFirstScheduling solver = new ShortestJobFirstScheduling();
            solver.solve(processes, contextSwitch);
            System.out.println("Shortest Job First Algorithm");
            System.out.println("Average waiting time = " + solver.averageWatingTime);
            System.out.println("Average turn around time = " + solver.averageTurnAroundTime);
            break;
        }
        case 2:{
            System.out.print("Context Switch Time : ");
            Integer contextSwitch = scan.nextInt();
            Process[] processes = new Process[numberOfProcess];
            for (int i = 0; i < numberOfProcess; i++) {
                processes[i] = new Process();
                processes[i].name = "P" + String.valueOf(i + 1);
                System.out.println("Process Name : " + processes[i].name);
                System.out.print("Process Arrival Time : ");
                processes[i].arrivalTime = scan.nextInt();
                System.out.print("Process Burst Time : ");
                processes[i].burstTime = scan.nextInt();
                System.out.print("Process Quantum : ");
                processes[i].quantum = scan.nextInt();
                System.out.println("-------------------------");
            }
            RoundRobinScheduling solver = new RoundRobinScheduling();
            solver.solve(processes, contextSwitch, 20);
            System.out.println("Round Robin Algorithm");
            System.out.println("Average waiting time = " + solver.averageWatingTime);
            System.out.println("Average turn around time = " + solver.averageTurnAroundTime);
            break;
        }
        case 3:{
            scan = new Scanner(System.in);
            Process[] processes = new Process[numberOfProcess];
            for (int i = 0; i < numberOfProcess; i++) {
                processes[i] = new Process();
                processes[i].name = "P" + String.valueOf(i + 1);
                System.out.println("Process Name : " + processes[i].name);
                System.out.print("Process Arrival Time : ");
                processes[i].arrivalTime = scan.nextInt();
                System.out.print("Process Burst Time : ");
                processes[i].burstTime = scan.nextInt();
                System.out.print("Process Priority : ");
                processes[i].priority = scan.nextInt();
                System.out.println("-------------------------");
            }
            PriorityScheduling solver = new PriorityScheduling();
            solver.solve(processes, 0);
            System.out.println("Priority Algorithm");
            System.out.println("Average waiting time = " + solver.averageWatingTime);
            System.out.println("Average turn around time = " + solver.averageTurnAroundTime);
            break;
        }
    }
    
    }
}