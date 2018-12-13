package mainPackage;

/**
 *
 * @author Eldeeb
 */
public class Process {
    public String name;
    public int priority;
    public int arrival_time;
    public int completon_time;
    public int tunraround_time;
    public int burst_time;
    public int waiting_time;
    
    public Process(String n ,int a , int b , int p){
        this.name = n;
        this.arrival_time = a;
        this.burst_time = b;
        this.priority = p;
    }
    
    
}
