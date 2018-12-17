package mainPackage;

import static java.lang.Math.ceil;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Chaotic
 */
public class AGScheduling {
    int number_of_processes = 0;
    Scanner input = new Scanner(System.in);
    Vector<process> queue = new Vector<process>();
    Vector<process> queue1 = new Vector<process>();
    Vector<process> RR = new Vector<process>();
    Vector<Quantums>Q=new Vector<Quantums>();
    Vector<String>names=new Vector<String>();

    public static class process implements Comparable<process> {

        public String name;
        public int arrival_time;
        public float burst_time;
        public int waiting_time;
        public int quantum;
        public int quantum_counter;
        public int turn_around;

        public process() {
            name = "";
            arrival_time = 0;
            burst_time = 0;
            waiting_time = 0;
            quantum = 0;
            quantum_counter=0;
            turn_around=0;
        }

        public process(String x, int y, float z, int w) {
            name = x;
            arrival_time = y;
            burst_time = z;
            waiting_time = 0;
            quantum = w;
            quantum_counter=0;
            turn_around=(int) z;
        }

        @Override
        public int compareTo(process o) {

            return (int) (burst_time - o.burst_time);

        }

    }
    public static class Quantums{
    public int []q;
    Quantums(){
    q=new int [4];
    }
    }

    public static Vector<process> sortprocesses(Vector<process> queue) {
        Vector<process> x = queue;
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.size(); j++){
                if (x.elementAt(i).arrival_time < x.elementAt(j).arrival_time) {
                    process t = new process();
                    t.name = x.elementAt(i).name;
                    t.burst_time = x.elementAt(i).burst_time;
                    t.arrival_time = x.elementAt(i).arrival_time;
                    t.waiting_time = x.elementAt(i).waiting_time;
                    t.quantum = x.elementAt(i).quantum;
                    t.quantum_counter=x.elementAt(i).quantum_counter;
                    t.turn_around=x.elementAt(i).turn_around;
                    x.elementAt(i).name = x.elementAt(j).name;
                    x.elementAt(i).burst_time = x.elementAt(j).burst_time;
                    x.elementAt(i).arrival_time = x.elementAt(j).arrival_time;
                    x.elementAt(i).waiting_time = x.elementAt(j).waiting_time;
                    x.elementAt(i).quantum = x.elementAt(j).quantum;
                    x.elementAt(i).quantum_counter=x.elementAt(j).quantum_counter;
                    x.elementAt(i).turn_around=x.elementAt(j).turn_around;

                    x.elementAt(j).name = t.name;
                    x.elementAt(j).burst_time = t.burst_time;
                    x.elementAt(j).arrival_time = t.arrival_time;
                    x.elementAt(j).waiting_time = t.waiting_time;
                    x.elementAt(j).quantum = t.quantum;
                    x.elementAt(j).quantum_counter=t.quantum_counter;
                    x.elementAt(j).turn_around=t.turn_around;

                }
            }
        }

        return x;
    }
    public static Vector<process> sortprocesses1(Vector<process> queue) {
        Vector<process> x = queue;
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.size(); j++) {
                if (x.elementAt(i).burst_time < x.elementAt(j).burst_time) {
                     process t = new process();
                    t.name = x.elementAt(i).name;
                    t.burst_time = x.elementAt(i).burst_time;
                    t.arrival_time = x.elementAt(i).arrival_time;
                    t.waiting_time = x.elementAt(i).waiting_time;
                    t.quantum = x.elementAt(i).quantum;
                    t.quantum_counter=x.elementAt(i).quantum_counter;
                    t.turn_around=x.elementAt(i).turn_around;
                    x.elementAt(i).name = x.elementAt(j).name;
                    x.elementAt(i).burst_time = x.elementAt(j).burst_time;
                    x.elementAt(i).arrival_time = x.elementAt(j).arrival_time;
                    x.elementAt(i).waiting_time = x.elementAt(j).waiting_time;
                    x.elementAt(i).quantum = x.elementAt(j).quantum;
                    x.elementAt(i).quantum_counter=x.elementAt(j).quantum_counter;
                    x.elementAt(i).turn_around=x.elementAt(j).turn_around;

                    x.elementAt(j).name = t.name;
                    x.elementAt(j).burst_time = t.burst_time;
                    x.elementAt(j).arrival_time = t.arrival_time;
                    x.elementAt(j).waiting_time = t.waiting_time;
                    x.elementAt(j).quantum = t.quantum;
                    x.elementAt(j).quantum_counter=t.quantum_counter;
                    x.elementAt(j).turn_around=t.turn_around;
                }
            }
        }
        return x;
    }

    public static Vector<process> shift(Vector<process> queue) {
        for (int i = 0; i < queue.size() - 1; i++) {
            process t = new process();
            t.name = queue.elementAt(i).name;
            t.burst_time = queue.elementAt(i).burst_time;
            t.arrival_time = queue.elementAt(i).arrival_time;
            t.waiting_time = queue.elementAt(i).waiting_time;
            t.quantum = queue.elementAt(i).quantum;
            t.quantum_counter=queue.elementAt(i).quantum_counter;
            t.turn_around=queue.elementAt(i).turn_around;

            queue.elementAt(i).name = queue.elementAt(i + 1).name;
            queue.elementAt(i).burst_time = queue.elementAt(i + 1).burst_time;
            queue.elementAt(i).arrival_time = queue.elementAt(i + 1).arrival_time;
            queue.elementAt(i).waiting_time = queue.elementAt(i + 1).waiting_time;
            queue.elementAt(i).quantum = queue.elementAt(i+1).quantum;
            queue.elementAt(i).quantum_counter=queue.elementAt(i+1).quantum_counter;
            queue.elementAt(i).turn_around=queue.elementAt(i+1).turn_around;

            queue.elementAt(i + 1).name = t.name;
            queue.elementAt(i + 1).burst_time = t.burst_time;
            queue.elementAt(i + 1).arrival_time = t.arrival_time;
            queue.elementAt(i + 1).waiting_time = t.waiting_time;
            queue.elementAt( i + 1 ).quantum = t.quantum;
            queue.elementAt(i+1).turn_around=t.turn_around;
            queue.elementAt( i + 1 ).quantum_counter=t.quantum_counter;


        }
        return queue;

    }

    public static Vector<process> increase_w(Vector<process> queue) {
        for (int i = 1; i < queue.size(); i++) {
            queue.elementAt(i).waiting_time++;
        }
        return queue;

    }
    public static int find(Vector<process>queue,String name){
    for(int i=0;i<queue.size();i++)
        if(queue.elementAt(i).name==name) return i;
    return -1;
    }
    

    public AGScheduling() {
        
        System.out.print("Enter the number of process");
        number_of_processes = input.nextInt();
        String x;
        int y;
        int sum=0;
        int w;
        int z;
        for (int i = 0; i < number_of_processes; i++) {
            x = input.next();/// name
            y = input.nextInt(); /// arrival
            w = input.nextInt(); /// burst
            z = input.nextInt(); ///quantum
            queue.addElement(new process(x, y, w, z));
            sum+=w;
        }
        queue = sortprocesses(queue); /// arrival sort
        for(int i=0;i<queue.size();i++)
            names.addElement(queue.elementAt(i).name);
       /* for(int i=0;i<queue.size();i++){
         System.out.println(queue.elementAt(i).name+" "+queue.elementAt(i).quantum);
         }*/
        
        // System.out.println(queue.elementAt(0).name+" "+ceil(.5*queue.elementAt(0).quantum));

        int ptr = 0;
        process last_first = new process();
        String [] Gant=new String[sum];
        for (int i = 0; RR.size() != queue.size(); i++) {
            for (int j = ptr; j < queue.size(); j++) {
                if (queue.elementAt(j).arrival_time == i) {
                    queue1.addElement(queue.elementAt(j));
                    ptr++;
                }
            }
         
            if (queue1.size() > 0) {

                if (queue1.elementAt(0).quantum_counter < ceil(0.5 * queue1.elementAt(0).quantum)) {
                    
                    queue1.elementAt(0).quantum_counter++;
                    queue1.elementAt(0).burst_time--;
                    System.out.print(queue1.elementAt(0).name+" ");
                    queue1 = increase_w(queue1);
                    if (queue1.elementAt(0).burst_time == 0) {
                        RR.addElement(queue1.elementAt(0));
                        queue1.elementAt(0).quantum=0;
                        queue1.elementAt(0).quantum_counter=0;
                        queue1.remove(0); 
                    }
                    
                    
                   if(queue1.size()>0) last_first = queue1.elementAt(0);
                   
                }
                
                else if (queue1.elementAt(0).quantum_counter >= ceil(0.5 * queue1.elementAt(0).quantum)) {
                    Collections.sort(queue1);
                    if (queue1.elementAt(0).name == last_first.name) {
                        queue1.elementAt(0).quantum_counter++;
                        queue1.elementAt(0).burst_time--;
                        System.out.print(queue1.elementAt(0).name+" ");

                        queue1 = increase_w(queue1);
                        if (queue1.elementAt(0).burst_time == 0) {
                            RR.addElement(queue1.elementAt(0));
                            queue1.elementAt(0).quantum=0;
                            queue1.elementAt(0).quantum_counter=0;
                            queue1.remove(0);
                        
                        }
                        else if (queue1.elementAt(0).quantum_counter == queue1.elementAt(0).quantum) {
                            queue1.elementAt(0).quantum++;
                            queue1.elementAt(0).quantum_counter=0;   
                            queue1 = shift(queue1);
                            i--;
                            
                            
               }
                        
               }
                    else{
                        int temp=0;
                        for(int k=0;k<queue1.size();k++){
                            if(queue1.elementAt(k).name==last_first.name){
                            break;
                            }
                        temp++;
                        }
                        
                        queue1.elementAt(temp).quantum+=(queue1.elementAt(temp).quantum-queue1.elementAt(temp).quantum_counter);
                        queue1.elementAt(temp).quantum_counter=0;
                        //System.out.println(queue1.elementAt(0).name+" "+queue1.elementAt(0).quantum);
            for(int k=temp;k<queue1.size()-1;k++){
            process t = new process();
            t.name = queue1.elementAt(k).name;
            t.burst_time = queue1.elementAt(k).burst_time;
            t.arrival_time = queue1.elementAt(k).arrival_time;
            t.waiting_time = queue1.elementAt(k).waiting_time;
            t.quantum = queue1.elementAt(k).quantum;
            t.quantum_counter=queue1.elementAt(k).quantum_counter;
            t.turn_around=queue1.elementAt(k).turn_around;
            queue1.elementAt(k).name = queue1.elementAt(k + 1).name;
            queue1.elementAt(k).burst_time = queue1.elementAt(k + 1).burst_time;
            queue1.elementAt(k).arrival_time = queue1.elementAt(k + 1).arrival_time;
            queue1.elementAt(k).waiting_time = queue1.elementAt(k + 1).waiting_time;
            queue1.elementAt(k).quantum = queue1.elementAt(k+1).quantum;
            queue1.elementAt(k).quantum_counter=queue1.elementAt(k+1).quantum_counter;
            queue1.elementAt(k).turn_around=queue1.elementAt(k+1).turn_around;

            queue1.elementAt(k + 1).name = t.name;
            queue1.elementAt(k + 1).burst_time = t.burst_time;
            queue1.elementAt(k + 1).arrival_time = t.arrival_time;
            queue1.elementAt(k + 1).waiting_time = t.waiting_time;
            queue1.elementAt( k + 1 ).quantum = t.quantum;
            queue1.elementAt( k + 1 ).quantum_counter=t.quantum_counter;
            queue1.elementAt( k + 1 ).turn_around=t.turn_around;

           }
           queue1.elementAt(0).quantum_counter++;
           queue1.elementAt(0).burst_time--;
           System.out.print(queue1.elementAt(0).name+" ");
           queue1 = increase_w(queue1);
           
                        if (queue1.elementAt(0).burst_time == 0) {
                            RR.addElement(queue1.elementAt(0));
                            queue1.elementAt(0).quantum=0;
                            queue1.remove(0);

                        }
                        else if (queue1.elementAt(0).quantum_counter == queue1.elementAt(0).quantum) {
                            queue1.elementAt(0).quantum++;
                            queue1.elementAt(0).quantum_counter=0;   
                            queue1 = shift(queue1);
                            i--;     
                    }
                        
                    }
                }
            
                
            }
            Quantums Q1 = new Quantums();
            int flag=0;
            for(int k=0;k<names.size();k++)
            {
                int t1=find(queue,names.elementAt(k));
                if(t1!=-1){
                    Q1.q[flag]=queue.elementAt(t1).quantum;
                }
                else {
                    Q1.q[flag]=0;
                }
                flag++;
            }
            Q.addElement(Q1);
            
            
               
            
        }
        System.out.println();
        int wait=0;
        int wait2=0;
        for(int i=0;i<Q.size();i++){
            for(int j=0;j<Q.elementAt(i).q.length;j++)
                System.out.print(Q.elementAt(i).q[j]+" ");
            System.out.println();

     }

        
        
        System.out.println();

        for(int i=0;i<RR.size();i++){
         wait+=RR.elementAt(i).waiting_time;
         RR.elementAt(i).turn_around+=RR.elementAt(i).waiting_time;
        wait2+=RR.elementAt(i).turn_around;
         System.out.println("Process name: "+RR.elementAt(i).name+" "+"Waiting_time: "+RR.elementAt(i).waiting_time+" Turn around: "+RR.elementAt(i).turn_around);
        }
        float avg=(float)(wait)/RR.size();
        float avg2=(float)(wait2)/RR.size();
        System.out.println("Average Waiting time: "+avg);
        System.out.println("Average Turn aroung time: "+avg2);
    }
}
