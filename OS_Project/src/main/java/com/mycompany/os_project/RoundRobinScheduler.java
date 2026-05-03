
package com.mycompany.os_project;
import java.util.*;
/**
 *
 * @author Abdelrahman And Magda
 */
public class RoundRobinScheduler {
    double quantum;
    int clock;
    
    List<String> ganttchart = new LinkedList<>() ;
    Queue<PCB> excuted = new LinkedList<>() ;
    public Queue<PCB>execute(Queue<PCB> processes){
    Queue<PCB> Ready = new LinkedList<>() ;
    ganttchart.add(String.valueOf(clock));
  // enqueue = add , serve = remove
   System.out.println("Process Queue:\n"+processes.toString());
   addToReadyQueue(processes,Ready);
  while(!processes.isEmpty()) 
  {
      PCB p = processes.poll();
    if(p.RTM <= quantum)
    {
        clock += p.RTM;
        addToReadyQueue(processes,Ready);
        p.RTM = 0;
        p.FinishProcess(clock);
        System.out.println("\n"+p.toString()+"\n");
        ganttchart.add("-P"+String.valueOf(p.PID)+"-"+clock);
        excuted.add(p);
    }else
    {
        clock += quantum;
       addToReadyQueue(processes,Ready); 
        p.updateRTM(quantum);
        System.out.println("\n"+p.toString()+"\n");
        ganttchart.add("-P"+String.valueOf(p.PID)+"-"+clock);
        processes.add(p);       
    }
     System.out.println("Process Queue:\n"+processes.toString());
    
    }
    System.out.println("\nGantChatt: "+String.join("", ganttchart));
    System.out.println(excuted);
    return excuted;
    }

    private void addToReadyQueue(Queue<PCB> processes,Queue<PCB> Ready)
    {
    for (PCB process:processes)
        {
            if(process.AT <= clock)
                Ready.add(process);
        }   
    }
    public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }
    
}
