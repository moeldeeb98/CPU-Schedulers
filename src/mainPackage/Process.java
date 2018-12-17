package mainPackage;

/**
 *
 * @author Eldeeb
 */
public class Process implements Comparable<Process> {

       public String name;
        public double burst_time;
        public int arrival_time;
        public int waiting_time;
        public int turnaround_time;

        public Process() {
            this.burst_time = 0;
            this.arrival_time = 0;
            this.name = "is not named";
            this.waiting_time = 0;
            this.turnaround_time=0;
        }

        public Process(String n, int at, int bt) {
            this.name = n;
            this.arrival_time = at;
            this.burst_time = bt;
            this.waiting_time = 0;
            this.turnaround_time = bt;

        }

       
        
        @Override
        public int compareTo(Process process) {
                return (int) (arrival_time- process.arrival_time);
        }
    }
