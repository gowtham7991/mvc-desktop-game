package view.screens;

import javax.swing.*;
import java.awt.*;

public class Demo{
  public static void main(String[] args) {
    JFrame frame = new JFrame("Adding a Rollover and Pressed Icon to a JButton Component");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    frame.add(panel,BorderLayout.CENTER);
    JButton cutbutton = new JButton(new ImageIcon("res/btnBg.g"));
    panel.add(cutbutton);
    ImageIcon rollover = new ImageIcon("res/btnBg.jpg");
    cutbutton.setRolloverIcon(rollover);
    ImageIcon press = new ImageIcon("res/btnBg.j");
    cutbutton.setPressedIcon(press);
    frame.setSize(400,400);
    frame.setVisible(true);
  }
}
