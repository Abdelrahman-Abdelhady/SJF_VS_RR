/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.os_project;
import java.util.*;
/**
 *
 * @author kolenaaa
 */
public class OS_Project {

    public static void main(String[] args) {
        System.out.println("Yala nebd2 el projectttt!!!");
        
        Queue<PCB> processes = new LinkedList<>();

        processes.add(new PCB(1, 0, 5));
        processes.add(new PCB(2, 1, 3));
        processes.add(new PCB(3, 2, 8));
        processes.add(new PCB(4, 3, 6));
        
    System.out.println("\n--- RoundRobin ---");
     RoundRobinScheduler  S = new RoundRobinScheduler(2);
     S.execute(processes);
     
        Queue<PCB> processes2 = new LinkedList<>();

        processes2.add(new PCB(1, 0, 5));
        processes2.add(new PCB(2, 1, 3));
        processes2.add(new PCB(3, 2, 8));
        processes2.add(new PCB(4, 3, 6));
     
     System.out.println("\n--- SJF ---");
     SjfScheduler sjf = new SjfScheduler();
     sjf.execute(processes);
     
    }
}
