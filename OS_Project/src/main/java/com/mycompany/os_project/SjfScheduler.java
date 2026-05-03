/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os_project;
import java.util.*;

public class SjfScheduler {

    int clock = 0;
    List<String> ganttChart = new LinkedList<>();
    Queue<PCB> executed = new LinkedList<>();

    public Queue<PCB> execute(List<PCB> allProcesses) {
        int n = allProcesses.size();
        int completed = 0;
        PCB currentProcess = null;

        while (completed < n) {
  
            PCB shortest = null;
           double  minRemainingTime = Integer.MAX_VALUE;

            for (PCB p : allProcesses) {
              
                if (p.AT <= clock && p.RTM > 0) {
                    if (p.RTM < minRemainingTime) {
                        
                        minRemainingTime = p.RTM ;
                        shortest = p;
                    }
                }
            }
            if (shortest == null) {
                clock++; 
                continue;
            }
            if (currentProcess == null || currentProcess.PID != shortest.PID) {
                ganttChart.add("Time " + clock + ": P" + shortest.PID);
                currentProcess = shortest;
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
        
        System.out.println("Gantt Chart (SJF): " + ganttChart);
        return executed;
    }

}
