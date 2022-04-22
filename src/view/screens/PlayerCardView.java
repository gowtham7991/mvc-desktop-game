package view.screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlayerCardView extends JButton {
  
//  private JLabel playerName;
  private JButton b;
  
  public PlayerCardView(String name){

    setText(name);
    setSize(150, 50);
    setVisible(true);
  }

}
