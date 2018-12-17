package mainPackage;


public class RoundRobinScheduling {

    public Integer totalWatingTime = 0;
    public Integer totalTurnAroundTime = 0;
    public Double averageWatingTime = 0.0;
    public Double averageTurnAroundTime = 0.0;

    private void sort(Process[] processes) {
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process t = new Process();
                    t = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = t;
                }
            }
        }
    }

    private void simulateProcesses(Process[] processes, Integer contextSwitch, Integer timeQuantum) {

        for (int i = 0; i < processes.length; i++) {
                processes[i].remainingTime = processes[i].burstTime;
        }
        Integer currentTime = 0;
        Process previousProcess = null;
        boolean complete = false;
        while (!complete) {
            complete = true;
            for (int i = 0; i < processes.length; i++) {
                if (processes[i].arrivalTime > currentTime) {
                    i--;
                    currentTime++;
                    continue;
                }
                if (processes[i].remainingTime > 0) {
                    complete = false;
                    if (previousProcess != null && !previousProcess.name.equals(processes[i].name)) {
                        if (contextSwitch > 0) {
                            System.out.println("from time " + currentTime + " to time " + (currentTime + contextSwitch)
                            + " is context switch time");
                        }
                        currentTime += contextSwitch;
                    }
                    if (processes[i].remainingTime > timeQuantum) {
                            System.out.println("from time " + currentTime + " to time " + (currentTime + timeQuantum)
                                            + " process " + processes[i].name + " running");
                            currentTime += timeQuantum;
                            processes[i].remainingTime -= timeQuantum;
                    } else {
                        System.out.println("from time " + currentTime + " to time " + (currentTime + processes[i].remainingTime)
                        + " process " + processes[i].name + " running");
                        System.out.println(processes[i].name + " completed");
                        currentTime += processes[i].remainingTime;
                        processes[i].finishTime = currentTime;
                        processes[i].waitingTime = currentTime - processes[i].burstTime;
                        processes[i].remainingTime = 0;
                        processes[i].turnAroundTime = processes[i].burstTime + processes[i].waitingTime;
                    }
                    previousProcess = processes[i];
                }
            }
        }
    }

    private void calculateAverageTime(Process[] processes, Integer contextSwitch, Integer timeQuantum) {

        System.out.println("Processes Simulation as follow : ");
        simulateProcesses(processes, contextSwitch, timeQuantum);
        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");
        for (int i = 0; i < processes.length; i++) {
                totalWatingTime += processes[i].waitingTime;
                totalTurnAroundTime += processes[i].turnAroundTime;
                System.out.println(" " + processes[i].name + "\t\t" + processes[i].burstTime + "\t "
                                + processes[i].waitingTime + "\t\t " + processes[i].turnAroundTime);
        }
        averageWatingTime = 1.0 * totalWatingTime / processes.length;
        averageTurnAroundTime = 1.0 * totalTurnAroundTime / processes.length;
    }

    public void solve(Process[] processes, Integer contextSwitch, Integer timeQuantum) {
        sort(processes);
        calculateAverageTime(processes, contextSwitch, timeQuantum);
    }

}
