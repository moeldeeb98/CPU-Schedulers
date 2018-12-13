package mainPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eldeeb
 */
public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        
        // read the number of process
        System.out.print("Enter the number of process : ");
        int numberOfProcess = s.nextInt();
        
        // read the time quantum
        System.out.print("Enter the Round Robin Time Quantum : ");
        int timeQuantum = s.nextInt();
        
        // read the context switch time
        System.out.print("Enter The Context Switch Time : ");
        int contextSwitch = s.nextInt();
        
        System.out.println("----------------------------------------------------");
        
        ArrayList<Process> arr = new ArrayList<>();
        
        // read the process attribute
        for(int i=0; i<numberOfProcess; i++){
            // read the process name
            System.out.print("Enter the name of the process #"+(i+1) +" : ");
            String name = s.next();
            
            // read the Arrival Time
            System.out.print("Enter Arrival Time of the process #"+(i+1) +" : ");
            int arrival_time = s.nextInt();
            
            // read the Burst Time
            System.out.print("Enter the Burst Time of the process #"+(i+1) +" : ");
            int burst_time = s.nextInt();
            
            // read the Priority
            System.out.print("Enter the Priority of the process #"+(i+1) +" : ");
            int priority_time = s.nextInt();
            
            System.out.println("----------------------------------------------------");
            
        }
    }
}
