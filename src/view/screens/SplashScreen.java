package view.screens;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * This class represents the first screen that appears when the game starts. It
 * enhance the user experience of the game.
 * 
 * serialVersionUID attribute to remember versions of a Serializable class to
 * verify that a loaded class and the serialized object are compatible.
 *
 */
public class SplashScreen extends JFrame {
  private static final long serialVersionUID = 1L;
  private final Color customColor;
  private final JProgressBar progressBar;
  private JLabel title;
  private JLabel credits;

  /**
   * This is the constructor of the class.
   * 
   */
  public SplashScreen() {
    customColor = new Color(211, 211, 243);
    progressBar = new JProgressBar();
    createGui();
    addImage();
    addText();
    addProgressBar();
    runningPbar();
  }

  private void createGui() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setUndecorated(true);
    this.getContentPane().setLayout(null);
    this.setSize(600, 400);
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(customColor);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void addImage() {
    JLabel gif = new JLabel(
        new ImageIcon(getClass().getClassLoader().getResource("assets/marvel.gif")));
    gif.setSize(600, 400);
    this.add(gif);
  }

  private void addText() {
    title = new JLabel();
    credits = new JLabel();
    title.setText(" ");
    title.setFont(new Font("Hack", Font.BOLD, 20));
    title.setForeground(Color.BLUE);
    this.getContentPane().add(title);
    title.setBounds(180, 60, 300, 80);

    credits.setText(" ");
    credits.setFont(new Font("Hack", Font.BOLD, 20));
    credits.setForeground(Color.BLACK);
    this.getContentPane().add(credits);
    credits.setBounds(185, 160, 300, 180);
    this.setVisible(true);
  }

  private void addProgressBar() {
    progressBar.setBounds(140, 360, 300, 20);
    progressBar.setBorderPainted(true);
    progressBar.setStringPainted(true);
    progressBar.setBackground(Color.WHITE);
    progressBar.setForeground(Color.BLACK);
    progressBar.setValue(0);
    this.add(progressBar);
  }

  private void runningPbar() {
    int i = 0;
    while (i <= 100) {
      try {
        Thread.sleep(10);
        progressBar.setValue(i);
        i++;
        if (i == 100) {
          this.dispose();
        }
      } catch (InterruptedException in) {
        this.dispose();
      }
    }
  }
}