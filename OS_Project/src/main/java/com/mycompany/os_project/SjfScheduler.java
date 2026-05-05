package com.mycompany.os_project;

import java.util.*;
/**
 *
 * @author Rodina
 */
public class SjfScheduler {

    int clock = 0;
    List<String> ganttChart = new LinkedList<>();
    Queue<PCB> executed = new LinkedList<>();

    public String execute(Queue<PCB> processesQueue) {
        clock = 0;                  
        ganttChart.clear();         
        executed.clear();            
        Queue<PCB> Copy_processes = new LinkedList<>(SchedulerUtils.deepCopyQueue(processesQueue));
        List<PCB> allProcesses = new ArrayList<>(Copy_processes);

        int n = allProcesses.size();
        int completed = 0;
        PCB currentProcess = null;

        while (completed < n) {
            List<PCB> readyQueue = new ArrayList<>();
            for (PCB p : allProcesses) {
                if (p.AT <= clock && p.RTM > 0) {
                    readyQueue.add(p);
                }
            }
            System.out.println("\nProcess Queue:");
            System.out.println(readyQueue.toString());

            PCB shortest = null;
            double minRemainingTime = Double.MAX_VALUE;

            for (PCB p : allProcesses) {
                if (p.AT <= clock && p.RTM > 0) {
                    if (p.RTM < minRemainingTime) {
                        minRemainingTime = p.RTM;
                        shortest = p;
                    }
                }
            }
            if (shortest == null) {
                clock++;
                continue;
            }
            System.out.println("\nExecuting: " + shortest.toString());
            if (currentProcess == null || currentProcess.PID != shortest.PID) {
                ganttChart.add(clock + "-P" + shortest.PID);
                currentProcess = shortest;
            }
            if (!shortest.started) {
                shortest.calculateRT(clock);
                shortest.started = true;
            }
            shortest.RTM--;
            clock++;
            if (shortest.RTM == 0) {
                completed++;
                shortest.FinishProcess(clock);
                executed.add(shortest);
                currentProcess = null;
                System.out.println(">>> Process P" + shortest.PID + " finished at Time " + clock);
            }
        }
        String finalGantt = String.join("-", ganttChart) + "-" + clock;
        System.out.println("\nFinal Gantt Chart: [" + finalGantt + "]");

        AverageWaitingTime(executed);
        AverageTAT(executed);
        AverageRT(executed);

         return "\nGantChart: " + String.join("", finalGantt)+
             "\n"+AverageWaitingTime(executed)+
             "\n"+AverageTAT(executed)+
             "\n"+AverageRT(executed);
    }

   public String AverageWaitingTime(Queue<PCB> finishedProcesses) {
    double totalWaitingTime = 0;
    
    for (PCB p : finishedProcesses) { 
        totalWaitingTime += p.WT;
    }
    return "Average Waiting Time = " + String.format("%.2f", totalWaitingTime / finishedProcesses.size()); 
    }
public String AverageTAT(Queue<PCB> finishedProcesses) {
    double totalTAT = 0;
    
    for (PCB p : finishedProcesses) { 
        totalTAT += p.TAT;
    }
    return "Average TAT = " + String.format("%.2f", totalTAT / finishedProcesses.size()); 
}
public String AverageRT(Queue<PCB> finishedProcesses) {
    double totalRT = 0;
    
    for (PCB p : finishedProcesses) { 
        totalRT += p.RT;
    }
    return "Average RT = " + String.format("%.2f", totalRT / finishedProcesses.size()); 
}

}

