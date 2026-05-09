
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

    
    
      private String addToReadyQueue(Queue<PCB> Copy_processes, Queue<PCB> Ready) {
        Iterator<PCB> p = Copy_processes.iterator();
        while (p.hasNext()) {
            PCB process = p.next();
            if (process.AT <= clock) {
                Ready.add(process);
                p.remove();
            }
        }
        return "Ready Queue at clock = "+this.clock+" "+Ready.toString()+"\n";
    }
    
    public String execute(Queue<PCB> processes,Queue<PCB> excuted) {
        clock = 0; // reset clock
        ganttchart.clear(); 
        excuted.clear(); 
        Queue<PCB> Copy_processes = new LinkedList<>(SchedulerUtils.deepCopyQueue(processes));
        Queue<PCB> Ready = new LinkedList<>();
        String ReadyQueue = new String();
        ganttchart.add(String.valueOf(clock));
        // enqueue = add , serve = remove
        ReadyQueue+= addToReadyQueue(Copy_processes, Ready);
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
                ReadyQueue+= addToReadyQueue(Copy_processes, Ready);

                p.RTM = 0;
                p.FinishProcess(clock);
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                excuted.add(p);
            } else {
               for (int i = 0; i < quantum && p.RTM > 0; i++) {
                    clock++;        
                    p.updateRTM(1);
                    ReadyQueue+= addToReadyQueue(Copy_processes, Ready);
                }
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                Ready.add(p);
            }
            

        }
        
        
        
     
     
     
     
     return "GantChart: " + String.join("", ganttchart)+
             "\n Average Waiting Time = "+String.format("%.2f",AverageWaitingTime(excuted))+
             "\n Average TAT = "+String.format("%.2f",AverageTAT(excuted))+
             "\n Average RT ="+String.format("%.2f",AverageRT(excuted))+
             "\n\n"+ReadyQueue;
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

public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }

    public RoundRobinScheduler() {
    }

}