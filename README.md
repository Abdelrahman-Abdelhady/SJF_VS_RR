1. Project Description

This application is a Windows-based simulator written in Java. It allows users to compare Round Robin and Shortest Job First (Preemptive) algorithms side-by-side. The tool calculates all necessary OS metrics (Waiting Time, Turnaround Time, and Response Time) and visualizes the process execution using Gantt Chart

2. Prerequisites
• Operating System: Windows 10/11.
• IDE: NetBeans IDE (Version 8.2 or higher, including Apache NetBeans).
• Java: JDK 8, 11, or 17.

3. How to Run

    
4.	Download & Extract:

 Unzip the project folder to your Desktop or C:\ drive.
   
Open Project: * If you have NetBeans, go to File > Open Project and select the folder.

• If you just want to run the code via Command Prompt:

• Open cmd in the project directory.

• Compile: javac com/mycompany/os_project/*.java

• Run: java com.mycompany.os_project.Project_GUI

	App Usage:
   
• Fill in the PID, Arrival, and Burst fields.

• Click Add Process to insert data into the table.

• Enter the Quantum value for Round Robin.

• Press Execute to see the results in the output areas.

6. Project Folder Structure
   
• Project_GUI.java: The main Windows form and user interface.

• PCB.java: Handles all process data and mathematical calculations.

• RoundRobinScheduler.java: Logic for time-slicing.

• SjfScheduler.java: Logic for preemptive shortest job first.

• SchedulerUtils.java: Contains the "Deep Copy" tool to ensure fair comparison on Windows.

7. Features Tested
   
• Gantt Chart Generation: Visual timeline of process execution.

• Metrics Calculation: Automatic calculation for Average Waiting Time, Turnaround Time, and Response Time.

• Validation: Error messages for invalid inputs (empty fields or negative numbers).

8.Team members
عبدالرحمن ايهاب حسان عبدالهادي     20240511


محمد ياسر حسن محمد محمد الحداد    20240886

عبدالغني محمد عبدالغني ابراهبم       20240570

هند حسين عبدالتواب محمد          20241104


رودينا ايهاب محمد بيومي           20240366


مني محمد رافت                 20241024


ماجده محمد احمد عبدالمعطي         20240750
   
