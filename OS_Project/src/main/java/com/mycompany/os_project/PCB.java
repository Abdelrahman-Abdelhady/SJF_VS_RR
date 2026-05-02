package com.mycompany.os_project;

public class PCB {
    
    //Attributes
    int PID;
    double AT;
    double BT;
    double RTM;
    double CT;  // Abdelrahman (the cpu clock value when the proccess hhave completed)
    double TAT; // Abdelghani (total time the process spent in the system, from arrival to completion.)
    double WT;  // Abdelghani
    double RT;  // Abdelrahman (Time from the arrival till the proccess is first excuted by the CPU)

    public PCB(int PID, double AT, double BT, double RTM, double CT, double TAT, double WT, double RT) {
        this.PID = PID;
        this.AT = AT;
        this.BT = BT;
        this.RTM = RTM;
        this.CT = CT;
        this.TAT = TAT;
        this.WT = WT;
        this.RT = RT;
    }

    public PCB(int PID, double AT, double BT) {
        this.PID = PID;
        this.AT = AT;
        this.BT = BT;
        RTM = BT;
    }
    
    // Method that Calculates the Completion time
    void calculateCT(double finishTime)
    {
        this.CT = finishTime;
    }  

    // Method that Calculates the Response time
    void calculateRT(double firstStartTime)
    {
        this.RT = firstStartTime - this.AT;
    }
    
    
}
