
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os_project;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class RoundRobinScheduler {
    double quantum;
    int clock;
    Queue<PCB> excuted = new LinkedList<>() ;
    public Queue<PCB>execute(Queue<PCB> processes){
         
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
        excuted.add(p);
         
        
        
       
    }else
    {
        clock += quantum;
        p.updateRTM(quantum);
        System.out.println("\n"+p.toString()+"\n");
        processes.add(p);
        
         
    }
     System.out.println("Process Queue:\n"+processes.toString());
    
}
    return excuted;
    }

    public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }
    
}
