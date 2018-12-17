package mainPackage;


public class Process {
	public String name;
	public Integer arrivalTime;
	public Integer burstTime;
	public Integer priority;
	public double quantum;
	public Integer waitingTime;
	public Integer turnAroundTime;
	public Integer remainingTime;
	public Integer finishTime;

	public Process() {
		this.arrivalTime = 0;
		this.burstTime = 0;
		this.priority = 0;
		this.waitingTime = 0;
		this.turnAroundTime = 0;
		this.remainingTime = 0;
		this.finishTime = 0;
		}
	
		@Override
	public String toString() {
            return "Process [name=" + name + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime + ", priority=" + priority + ", waitingTime=" + waitingTime + ", turnAroundTime=" + turnAroundTime + ", remainingTime="
			+ remainingTime + ", finishTime=" + finishTime + "]";
	}

}
