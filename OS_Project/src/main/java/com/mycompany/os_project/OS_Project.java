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
        
        List<PCB> processes = Arrays.asList(
    new PCB(1, 0, 5, 5, 0, 0, 0, 0),
    new PCB(2, 1, 3, 3, 0, 0, 0, 0),
    new PCB(3, 2, 8, 8, 0, 0, 0, 0),
    new PCB(4, 3, 6, 6, 0, 0, 0, 0)
    );

    Queue<PCB> readyQueue = new LinkedList<>(processes);

    }
}
