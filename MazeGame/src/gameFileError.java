/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamesource;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author shirl
 */
public class gameFileError extends RuntimeException {
    
    public gameFileError() {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Your maze file ether had more than one player, or more than one exit.");
        }
}
