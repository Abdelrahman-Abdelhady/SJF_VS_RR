package com.mycompany.os_project;

public class PCB {
    
    //Attributes
    int PID;
    double AT;
    double BT;
    
    //should be calculated whenevever the Proccess enters Excution
    double RTM; //Remaining excution time for round robin
    
    //should be calulated when the proccess Finishes excution
    double CT;  // Abdelrahman (the CPU clock value when the proccess have completed)
    //should be calulated when the proccess Finishes excution
    double TAT; // Abdelghani (total time the process spent in the system, from arrival to completion.)
    double WT;  // Abdelghani (The total time a process spends waiting in the ready queue before and between CPU bursts.)
    //should be calulated when the proccess starts excution
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
    
    //Update RTM
     void updateRTM(double quantumTime)
    {
        this.RTM -= quantumTime;
    }
    
    
    
    // Method that Calculates the Total time in system 
    void calculateTAT()
    {
        this.TAT = this.CT - this.AT ;
    }  
    
    // Method that Calculates the Completion time
    void calculateWT()
    {
        this.WT = this.TAT - this.BT ;
    }  
     
    
}
