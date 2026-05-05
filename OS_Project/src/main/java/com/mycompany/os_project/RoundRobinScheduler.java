
package com.mycompany.os_project;

import java.util.*;

/**
 *
 * @author Abdelrahman And Magda
 */
public class RoundRobinScheduler {

    double quantum;
    int clock;
    
    List<String> ganttchart = new LinkedList<>();
    Queue<PCB> excuted = new LinkedList<>();

    
    
      private void addToReadyQueue(Queue<PCB> Copy_processes, Queue<PCB> Ready) {
        Iterator<PCB> p = Copy_processes.iterator();
        while (p.hasNext()) {
            PCB process = p.next();
            if (process.AT <= clock) {
                Ready.add(process);
                p.remove();
            }
        }
    }
    
    public String execute(Queue<PCB> processes) {
        clock = 0; // reset clock
        ganttchart.clear(); 
        excuted.clear(); 
        Queue<PCB> Copy_processes = new LinkedList<>(SchedulerUtils.deepCopyQueue(processes));
        Queue<PCB> Ready = new LinkedList<>();
        ganttchart.add(String.valueOf(clock));
        // enqueue = add , serve = remove
        System.out.println("Ready Queue:\n" + Ready.toString());
        addToReadyQueue(Copy_processes, Ready);
        while (!Ready.isEmpty() || !Copy_processes.isEmpty()) {
            //To account for idel state
            if (Ready.isEmpty() && !Copy_processes.isEmpty()) {
                clock++;
                addToReadyQueue(Copy_processes, Ready);
                continue;
            }

            PCB p = Ready.poll();
            if(!p.started)
            {
                p.calculateRT(clock);
                p.started = true;
                
            }
            if (p.RTM <= quantum) {
                
                
                clock += p.RTM;
                addToReadyQueue(Copy_processes, Ready);
                p.RTM = 0;
                p.FinishProcess(clock);
                System.out.println("\n" + p.toString() + "\n");
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                excuted.add(p);
            } else {
               for (int i = 0; i < quantum && p.RTM > 0; i++) {
                    clock++;        
                    p.updateRTM(1);
                    addToReadyQueue(Copy_processes, Ready);
                }
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                Ready.add(p);
            }
            System.out.println("Ready Queue:\n" + Ready.toString());

        }
        System.out.println("\nGantChatt: " + String.join("", ganttchart));
        System.out.println("\nGantChart: " + String.join("", ganttchart));
        System.out.println(excuted);
        //return excuted;
        
     
     
     
     
     return "\nGantChart: " + String.join("", ganttchart)+
             "\n"+AverageWaitingTime(excuted)+
             "\n"+AverageTAT(excuted)+
             "\n"+AverageRT(excuted);
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

public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }

    public RoundRobinScheduler() {
    }

}