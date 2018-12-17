package mainPackage;

import java.util.Vector;

public class ShortestJobFirstScheduling {

    public Integer totalWatingTime = 0;
    public Integer totalTurnAroundTime = 0;
    public Double averageWatingTime = 0.0;
    public Double averageTurnAroundTime = 0.0;

	
    Vector<Process> ThirdQueue = new Vector<Process>();

    private void sort(Process[] processes) {
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime || 	processes[j].arrivalTime == processes[j + 1].arrivalTime
                    && processes[j].burstTime > processes[j + 1].burstTime) {
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
		
        Process newObj = null;
        while (complete < processes.length) {
            newObj = new Process();
            for (int j = 0; j < processes.length; j++) {
                if ((processes[j].arrivalTime <= currentTime) && (processes[j].remainingTime < minRemainingTime)
                                && processes[j].remainingTime > 0) {
                    minRemainingTime = processes[j].remainingTime;
                    bestIndex = j;
                    findProcess = true;
                }
            }
            if (findProcess == false) {
                    currentTime++;		//law wla w7da el time bt3ha 2rb 3la el time current
                    continue;
            }
            if (previousProcess != null && !previousProcess.name.equals(processes[bestIndex].name)) {
                    if (previousProcess.remainingTime == 0) {
                            System.out.println("from time " + previousTime + " to time " + previousProcess.finishTime
                                            + " process " + previousProcess.name + " running");	
                            newObj.name = previousProcess.name;
                            newObj.burstTime = previousProcess.finishTime - previousTime;
                            System.out.println(previousProcess.name + " completed");

                    } else {
                            System.out.println("from time " + previousTime + " to time " + currentTime + " process "
                                            + previousProcess.name + " running");
                            newObj.name = previousProcess.name;
                            newObj.burstTime = currentTime - previousTime;
                    }
                    if (contextSwitch > 0) {
                            System.out.println("from time " + currentTime + " to time " + (currentTime + contextSwitch)
                                            + " is context switch time");
                    }
                    currentTime += contextSwitch;
                    previousTime = currentTime;
                    ThirdQueue.add(newObj);
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
        System.out.println("from time " + previousTime + " to time " + currentTime + " process " + previousProcess.name
                        + " running");
        newObj = new Process();
        newObj.name = previousProcess.name;
        newObj.burstTime = currentTime - previousTime;
        ThirdQueue.add(newObj);

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
            System.out.println(" " + process.name + "\t\t" + process.burstTime + "\t\t " + process.waitingTime + "\t\t"
                            + process.turnAroundTime);
        }
        averageWatingTime = 1.0 * totalWatingTime / processes.length;
        averageTurnAroundTime = 1.0 * totalTurnAroundTime / processes.length;
    }

    public void solve(Process[] processes, Integer contextSwitch) {
        sort(processes);
        calculateAverageTime(processes , contextSwitch);
    }
}
