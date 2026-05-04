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

    public void execute(Queue<PCB> processes) {
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
            if (p.RTM <= quantum) {
                clock += p.RTM;
                addToReadyQueue(Copy_processes, Ready);
                p.RTM = 0;
                p.FinishProcess(clock);
                System.out.println("\n" + p.toString() + "\n");
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                excuted.add(p);
            } else {
                clock += quantum;
                addToReadyQueue(Copy_processes, Ready);
                p.updateRTM(quantum);
                System.out.println("\n" + p.toString() + "\n");
                ganttchart.add("-P" + String.valueOf(p.PID) + "-" + clock);
                Ready.add(p);
            }
            System.out.println("Ready Queue:\n" + Ready.toString());

        }
        System.out.println("\nGantChart: " + String.join("", ganttchart));
        System.out.println(excuted);
        //return excuted;

        AverageWaitingTime(excuted);
        AverageTAT(excuted);
        AverageRT(excuted);
    }

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

    public RoundRobinScheduler(double quantum) {
        this.quantum = quantum;
    }

    public void AverageWaitingTime(Queue<PCB> finishedProcesses) {
        double totalWaitingTime = 0;

        for (PCB p : finishedProcesses) {
            totalWaitingTime += p.WT;
        }
        System.out.println("Average Waiting Time -Round Robin- = " + totalWaitingTime / finishedProcesses.size());
    }

    public void AverageTAT(Queue<PCB> finishedProcesses) {
        double totalTAT = 0;

        for (PCB p : finishedProcesses) {
            totalTAT += p.TAT;
        }
        System.out.println("Average TAT -Round Robin- = " + totalTAT / finishedProcesses.size());
    }

    public void AverageRT(Queue<PCB> finishedProcesses) {
        double totalRT = 0;

        for (PCB p : finishedProcesses) {
            totalRT += p.RT;
        }
        System.out.println("Average RT -Round Robin- = " + totalRT / finishedProcesses.size());
    }


}
