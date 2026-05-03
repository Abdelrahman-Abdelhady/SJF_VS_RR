package com.mycompany.os_project;
import java.util.*;

public class SjfScheduler {

    int clock = 0;
    List<String> ganttChart = new LinkedList<>();
    Queue<PCB> executed = new LinkedList<>();

public Queue<PCB> execute(Queue<PCB> processesQueue) {
    List<PCB> allProcesses = new ArrayList<>(processesQueue);
    int n = allProcesses.size();
    int completed = 0;
    PCB currentProcess = null;

    while (completed < n) {
        List<PCB> readyQueue = new ArrayList<>();
        for (PCB p : allProcesses) {
            if (p.AT <= clock && p.RTM > 0) readyQueue.add(p);
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

    String finalGantt =  String.join("-", ganttChart) + "-" + clock;
    System.out.println("\nFinal Gantt Chart: [" + finalGantt + "]");
    
    return executed;
}
}
