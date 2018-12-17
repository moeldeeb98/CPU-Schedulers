package mainPackage;

import java.util.ArrayList;


public class AG {

    public ArrayList<Process> FirstQueue;
    public ArrayList<Process> SecondQueue; 
    public ArrayList<Process> ThirdQueue;
	
    public AG(){
        FirstQueue = new ArrayList<Process>();
        SecondQueue = new ArrayList<Process>();
        ThirdQueue = new ArrayList<Process>();
    }
    
    public void addProcesses(Process [] arr){
        for(int i = 0;i<arr.length;i++)
                FirstQueue.add(arr[i]);
    }

    public void sortSecondQueue() {
        for (int i = 0; i < SecondQueue.size() - 1; i++) {
            for (int j = 0; j < SecondQueue.size() - i - 1; j++) {
                if (SecondQueue.get(j).arrivalTime > SecondQueue.get(j + 1).arrivalTime) {
                        Process temp = new Process();
                        temp = SecondQueue.get(j);
                        SecondQueue.set(j, SecondQueue.get(j + 1));
                        SecondQueue.set(j + 1, temp);
                }
            }
        }
        createSecondQueue(0);
    }

    public void createSecondQueue(int x) {
        Process current = new Process();
        current = SecondQueue.get(x);
        int k = x+1;
        for (int i = k; i < SecondQueue.size(); i++) {
            for (int j = k; j < SecondQueue.size() - i ; j++) {
                if (SecondQueue.get(j).burstTime > SecondQueue.get(j + 1).burstTime) {
                        if (SecondQueue.get(j).arrivalTime < current.burstTime) {
                                Process temp = new Process();
                                temp = SecondQueue.get(j);
                                SecondQueue.set(j, SecondQueue.get(j + 1));
                                SecondQueue.set(j + 1, temp);
                        }
                        else {
                                x++;
                                createSecondQueue(x);
                        }
                }
            }
        }

    }

    public Process [] creatArr(ArrayList<Process> arr){
        Process [] x = new Process [arr.size()];
        for(int i = 0;i<arr.size();i++)
            x[i] = arr.get(i);
        return x;
    }
	
    public void print() {
        for (int i = 0; i < SecondQueue.size(); i++) {
                System.out.println("Name: " + SecondQueue.get(i).name + "\tQuantum: " + SecondQueue.get(i).quantum);
        }
    }
	
    public void printThird() {
        for (int i = 0; i < ThirdQueue.size(); i++) {
            System.out.println("Name: " + ThirdQueue.get(i).name + "\tQuan: " + ThirdQueue.get(i).quantum);
        }
    }

    public void implFirst() {
        int pp = 0;
        while(FirstQueue.size()!=0) {
            pp = (int) Math.ceil(FirstQueue.get(0).quantum/4);
            //System.out.println("PP: " + pp);
            FirstQueue.get(0).burstTime -=pp;
            if(FirstQueue.get(0).burstTime != 0){
                    FirstQueue.get(0).quantum += 2;
                    SecondQueue.add(FirstQueue.get(0));
                    sortSecondQueue();
            }
            FirstQueue.remove(0);
        }
    }
	
    public void implSecond(){
        if (FirstQueue.size()!=0 ){
                implFirst();
        }
        int pp = 0;
        while (FirstQueue.size()==0 && SecondQueue.size()!=0) {
            pp = (int) Math.ceil(SecondQueue.get(0).quantum/4);
            //System.out.println("PP: " + pp);
            SecondQueue.get(0).burstTime -=pp;
            if(SecondQueue.get(0).burstTime != 0){
                    SecondQueue.get(0).quantum += SecondQueue.get(0).quantum/2;
                    ThirdQueue.add(SecondQueue.get(0));
            }
            SecondQueue.remove(0);
        } 
    }
	
    public void execute() {
        implFirst();
        print();

        implSecond();
        printThird();
        if (FirstQueue.size()!=0 ){
                implFirst();
        }
        if (SecondQueue.size() !=0 ){
                implSecond();
        }
        Process [] arr = creatArr(ThirdQueue);
        ShortestJobFirstScheduling obj = new ShortestJobFirstScheduling();
        obj.solve(arr, 0);
                   
    }

}
