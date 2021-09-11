/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamesource;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author shirl
 */
public class HighScore {

    public void addHighScore(String name, int min, int sec, int level) {
        try {
            String outData = "PlayerName: " + name + " Total Time for Levels:" + min + ":" + sec + "(Minutes:Seconds)" + "Level Reached:*" + level;
            PrintWriter out = new PrintWriter(new FileOutputStream("scores.txt", true));
            out.println("");
            out.println(outData);
            out.close();
        } catch (Exception ex) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Oh oh, your score could not be saved");
        }
    }
}
