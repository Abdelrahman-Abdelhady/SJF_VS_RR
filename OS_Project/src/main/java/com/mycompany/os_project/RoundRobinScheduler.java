
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
    public List<PCB> execute(Queue<PCB> processes){
         
  // enqueue = add , serve = remove
    for (PCB p : processes) {
    if(p.RTM <= quantum)
    {
        clock += p.RTM;
        p.RTM = 0;
        processes.remove(p);
        
    }else
    {
        p.updateRTM(quantum);
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    return processList;
    }
    
}
