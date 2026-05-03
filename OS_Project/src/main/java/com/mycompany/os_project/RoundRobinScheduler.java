
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
    ganttchart.add(String.valueOf(clock));
  // enqueue = add , serve = remove
   System.out.println("Process Queue:\n"+processes.toString());
  while(!processes.isEmpty()) 
  {
      PCB p = processes.poll();
    if(p.RTM <= quantum)
    {
        clock += p.RTM;
        p.RTM = 0;
        p.FinishProcess(clock);
        System.out.println("\n"+p.toString()+"\n");
        ganttchart.add("-P"+String.valueOf(p.PID)+"-"+clock);
        excuted.add(p);
    }else
    {
        clock += quantum;
        p.updateRTM(quantum);
        System.out.println("\n"+p.toString()+"\n");
        ganttchart.add("-P"+String.valueOf(p.PID)+"-"+clock);
        processes.add(p);       
    }
     System.out.println("Process Queue:\n"+processes.toString());
    
    }
    System.out.println("\nGantChatt: "+ganttchart.toString());
    return excuted;
    }


    public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }
    
}
