package mainPackage;


public class PriorityScheduling {

	public Integer totalWatingTime = 0;
	public Integer totalTurnAroundTime = 0;
	public Double averageWatingTime = 0.0;
	public Double averageTurnAroundTime = 0.0;

    private void sort(Process[] processes) {
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - 1; j++) {
                if (processes[j].priority > processes[j + 1].priority || processes[j].priority == processes[j + 1].priority && processes[j].burstTime > processes[j + 1].burstTime) {
                    Process t = new Process();
                    t = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = t;
                }
            }
        }
    }

    private void simulateProcesses(Process[] processes, Integer contextSwitch) {
        for (int i = 0; i < processes.length; i++) {
            processes[i].remainingTime = processes[i].burstTime;
        }
        Integer complete = 0;
        Integer currentTime = 0;
        Integer minRemainingTime = (int) 2e9;
        Integer bestIndex = -1;
        Boolean findProcess = false;
        Process previousProcess = null;
        Integer previousTime = 0;
        while (complete < processes.length) {
            for (int j = 0; j < processes.length; j++) {
                if ((processes[j].arrivalTime <= currentTime) && (processes[j].remainingTime < minRemainingTime)
                                && processes[j].remainingTime > 0) {
                        minRemainingTime = processes[j].remainingTime;
                        bestIndex = j;
                        findProcess = true;
                }
            }
            if (findProcess == false) {
                currentTime++;
                continue;
            }
            if (previousProcess != null && !previousProcess.name.equals(processes[bestIndex].name)) {
                if (previousProcess.remainingTime == 0) {
                        System.out.println("from time " + previousTime + " to time " + previousProcess.finishTime
                                        + " process " + previousProcess.name + " running");
                        System.out.println(previousProcess.name + " completed");
                } else {
                        System.out.println("from time " + previousTime + " to time " + currentTime + " process "
                                        + previousProcess.name + " running");
                }
                if (contextSwitch > 0) {
                        System.out.println("from time " + currentTime + " to time " + (currentTime + contextSwitch)
                                        + " is context switch time");
                }
                currentTime += contextSwitch;
                previousTime = currentTime;
            }
            processes[bestIndex].remainingTime--;
            minRemainingTime = processes[bestIndex].remainingTime;
            if (minRemainingTime == 0)
                    minRemainingTime = (int) 2e9;
            if (processes[bestIndex].remainingTime == 0) {
                    complete++;
                    findProcess = false;
                    processes[bestIndex].waitingTime = Math.max(0,
                                    currentTime + 1 - processes[bestIndex].burstTime - processes[bestIndex].arrivalTime);
                    processes[bestIndex].turnAroundTime = processes[bestIndex].burstTime + processes[bestIndex].waitingTime;
                    processes[bestIndex].finishTime = currentTime + 1;
            }
            currentTime++;
            previousProcess = processes[bestIndex];
        }
        System.out.println("from time " + previousTime + " to time " + currentTime + " process " + previousProcess.name + " running");
        if (previousProcess.remainingTime == 0) {
            System.out.println(previousProcess.name + " completed");
        }
    }

    private void calculateAverageTime(Process[] processes, Integer contextSwitch) {
        System.out.println("Processes Simulation as follow : ");
        simulateProcesses(processes, contextSwitch);
        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");
        for (Process process : processes) {
            totalWatingTime += process.waitingTime;
            totalTurnAroundTime += process.turnAroundTime;
            System.out.println(" " + process.name + "\t\t" + process.burstTime + "\t\t " + process.waitingTime + "\t\t" + process.turnAroundTime);
        }
        averageWatingTime = 1.0 * totalWatingTime / processes.length;
        averageTurnAroundTime = 1.0 * totalTurnAroundTime / processes.length;
    }

    public void solve(Process[] processes, Integer contextSwitch) {
        sort(processes);
        calculateAverageTime(processes, contextSwitch);
    }
}
