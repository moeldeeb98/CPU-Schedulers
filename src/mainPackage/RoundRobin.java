package mainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Eldeeb
 */
public class RoundRobin {
    public int number_of_processes = 0;
    public int quantum_time=0;
    public Scanner input = new Scanner(System.in);
    public ArrayList<Process> queue = new ArrayList();
    public ArrayList<Process> queue1 = new ArrayList();
    public ArrayList<Process> RR= new ArrayList<>();
    public int sum=0 ;
    public int counter = 0;
    //-------------------------------------------------------------
    
    public RoundRobin(){
        
        readRequiermants();

        Collections.sort(queue);
      
        String []gantt_chart=new String[sum];
        int ptr = 0;
        int ptr2=0;
        int temp = quantum_time;
        for (int i = 0; RR.size()!=queue.size(); i++) {
              for (int j = ptr; j < queue.size(); j++){
                  if (queue.get(j).arrival_time == i) {
                      queue1.add(queue.get(j));
                      ptr++;          
                  }
              }
            if(queue1.size()>0){
                queue1.get(0).burst_time--;
                counter++;
                gantt_chart[ptr2]=queue1.get(0).name;
                ptr2++;

                queue1=increase_waiting_time(queue1);
                if(queue1.get(0).burst_time>0&&counter == quantum_time){
                    queue1=shift(queue1);
                    counter=0;
                 }
                 else if(queue1.get(0).burst_time == 0&&counter<=quantum_time){
                    RR.add(queue1.remove(0));
                    counter = 0;
                 }
            }
        }
        int wait=0;
        int wait2=0;
        for(int i=0;i<gantt_chart.length;i++)
            System.out.print(gantt_chart[i]+ " ");
            System.out.println();
        for(int i=0;i<RR.size();i++){
        wait+=RR.get(i).waiting_time;
        RR.get(i).turnaround_time += RR.get(i).waiting_time;
        wait2 += RR.get(i).turnaround_time;
        System.out.println("Process name: " + RR.get(i).name + " Waiting time: " + RR.get(i).waiting_time + " Turn around time: " + RR.get(i).turnaround_time);
        }
        double wt_avg=(float)(wait)/RR.size();
        double tat_avg2=(float)(wait2)/RR.size();
        System.out.println("Average Waiting time: " + wt_avg);
        System.out.println("Average Turn aroung time: " + tat_avg2);
        
    }
    //--------------------------------------------------------------
    public ArrayList<Process> increase_waiting_time(ArrayList<Process>arr){
        for(int i=1;i<arr.size();i++)
        arr.get(i).waiting_time++;

        return arr;

    }
    //--------------------------------------------------------------
    public ArrayList<Process> shift(ArrayList<Process>queue){
     for(int i=0;i<queue.size()-1;i++){
            Process p=new Process();
            p.name=queue.get(i).name;
            p.burst_time=queue.get(i).burst_time;
            p.arrival_time=queue.get(i).arrival_time;
            p.waiting_time=queue.get(i).waiting_time;
            p.turnaround_time=queue.get(i).turnaround_time;
            queue.get(i).name=queue.get(i+1).name;
            queue.get(i).burst_time=queue.get(i+1).burst_time;
            queue.get(i).arrival_time=queue.get(i+1).arrival_time;
            queue.get(i).waiting_time=queue.get(i+1).waiting_time;
            queue.get(i).turnaround_time=queue.get(i+1).turnaround_time;
            queue.get(i+1).name=p.name;
            queue.get(i+1).burst_time=p.burst_time;
            queue.get(i+1).arrival_time=p.arrival_time;
            queue.get(i+1).waiting_time=p.waiting_time;
            queue.get(i+1).turnaround_time=p.turnaround_time;
        }
        return queue;
     }
    //--------------------------------------------------------------

    private void readRequiermants() {
        System.out.print("Number of process : ");
        number_of_processes = input.nextInt();
        System.out.print("Quantum Time : ");
        quantum_time=input.nextInt();
        
        for (int i = 0; i < number_of_processes; i++) {
            System.out.print("Name of process #" + (i+1) + ": ");
            String name = input.next();// name of the process
            System.out.print("Arrival Time of process #" + (i+1) + ": ");
            int at = input.nextInt(); // arrival time
            System.out.print("Burst  of process #" + (i+1) + ": ");
            int bt = input.nextInt(); // burst time
            queue.add(new Process(name, at, bt));
            sum += bt;
        }
    }

}
