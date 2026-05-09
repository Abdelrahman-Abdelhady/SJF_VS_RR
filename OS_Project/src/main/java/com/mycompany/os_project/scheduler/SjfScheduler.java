package com.mycompany.os_project;

import com.mycompany.os_project.PCB;
import java.util.*;
/**
 *
 * @author Rodina
 */
public class SjfScheduler {

    int clock = 0;
    List<String> ganttChart = new LinkedList<>();

    public String execute(Queue<PCB> processesQueue, Queue<PCB> executed ) {
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
            // بتشتغل لما يكون لسه بادئ او لما بتيجي بروسيس اقصر من ال شغاله
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
              
            }
        }
        String finalGantt = String.join("-", ganttChart) + "-" + clock;

         return "GantChart: " + String.join("", finalGantt)+
             "\n Average Waiting Time = "+String.format("%.2f",AverageWaitingTime(executed))+
             "\n Average TAT = "+String.format("%.2f",AverageTAT(executed))+
             "\n Average RT ="+String.format("%.2f",AverageRT(executed));
    }

   public double AverageWaitingTime(Queue<PCB> finishedProcesses) {
    double totalWaitingTime = 0;
    
    for (PCB p : finishedProcesses) { 
        totalWaitingTime += p.WT;
    }
    return  totalWaitingTime / finishedProcesses.size(); 
    }
public double AverageTAT(Queue<PCB> finishedProcesses) {
    double totalTAT = 0;
    
    for (PCB p : finishedProcesses) { 
        totalTAT += p.TAT;
    }
    return  totalTAT / finishedProcesses.size(); 
}
public double AverageRT(Queue<PCB> finishedProcesses) {
    double totalRT = 0;
    
    for (PCB p : finishedProcesses) { 
        totalRT += p.RT;
    }
    return  totalRT / finishedProcesses.size(); 
}

}

