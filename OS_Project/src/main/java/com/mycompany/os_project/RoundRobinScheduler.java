
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
    public List<PCB> execute(List<PCB> processes, int quantum){
    Queue<PCB> readyQueue = new LinkedList<>();
    List<PCB> processList = new ArrayList<>();
    for (PCB p : processes) {
    processList.add(new PCB(
            p.PID,
            p.AT,
            p.BT,
            p.BT,   
            0,     
            0,      
            0,      
            -1      
    ));
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    return processList;
    }
    
}
