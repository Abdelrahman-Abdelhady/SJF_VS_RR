package com.mycompany.os_project;

import com.mycompany.os_project.PCB;
import java.util.LinkedList;
import java.util.Queue;


public class SchedulerUtils {
    public static Queue<PCB> deepCopyQueue(Queue<PCB> processes) {
        Queue<PCB> copy = new LinkedList<>();
        for (PCB p : processes) {
            copy.add(new PCB(p)); 
        }
        return copy;
    }
    
    
}
