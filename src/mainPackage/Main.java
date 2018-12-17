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
        System.out.println("Select the scheduler from the following :");
        System.out.println("(1) RoundRoubin");
        System.out.println("(2) AG");
        System.out.println("(0) Exit");
        
        int choice = 0;
        choice = s.nextInt();
        
        switch (choice){
            case 1:
                RoundRobin rr = new RoundRobin();
                break;
            case 2:
                AGScheduling a = new AGScheduling();
                break;
            case 0:
                return;
        }
    }
}
