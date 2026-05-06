/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.os_project;
import javax.swing.SwingUtilities;
/**
 *
 * @author kolenaaa
 */
public class OS_Project {

    public static void main(String[] args) {
      // Use invokeLater to ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Project_GUI gui = new Project_GUI();
            gui.setTitle("OS Scheduler Simulation");
            gui.setLocationRelativeTo(null); // Center the window on screen
            gui.setVisible(true);
        });
    

    }
}
