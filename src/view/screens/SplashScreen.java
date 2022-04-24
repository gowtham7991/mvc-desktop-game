package view.screens;

import java.awt.*;
import javax.swing.*;
 
public class SplashScreen extends JFrame {
  private static final long serialVersionUID = 1L;
  private Color customColor;
  private String text = " ";
  private String c = " ";
  private JLabel title;
  private JLabel credits;
  private final JProgressBar progressBar;

  public SplashScreen() {
    customColor = new Color(211,211,243);
    progressBar = new JProgressBar();
    createGUI();
    addImage();
    addText();
    addProgressBar();
    runningPBar();
  }

  private void createGUI(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setUndecorated(true);
    this.getContentPane().setLayout(null);
    this.setSize(600,400);
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(customColor);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void addImage(){
    JLabel gif = new JLabel(new ImageIcon(getClass().getClassLoader()
        .getResource("assets/marvel.gif")));
    gif.setSize(600, 400);
    this.add(gif);
  }

  private void addText() {
    title = new JLabel();
    credits = new JLabel();
    title.setText(text);
    title.setFont(new Font("Hack",Font.BOLD,20));//Setting font size of text
    title.setForeground(Color.BLUE);//Setting foreground color
    this.getContentPane().add(title);//adding text to the frame
    title.setBounds(180, 60, 300, 80);//Setting size and location

    credits.setText(c);
    credits.setFont(new Font("Hack",Font.BOLD,20));//Setting font size of text
    credits.setForeground(Color.BLACK);//Setting foreground color
    this.getContentPane().add(credits);//adding text to the frame
    credits.setBounds(185, 160, 300, 180);//Setting size and location
    this.setVisible(true);
  }

  private void addProgressBar(){
    progressBar.setBounds(140,360,300,20);
    progressBar.setBorderPainted(true);
    progressBar.setStringPainted(true);
    progressBar.setBackground(Color.WHITE);
    progressBar.setForeground(Color.BLACK);
    progressBar.setValue(0);
    this.add(progressBar);
  }

  private void runningPBar(){
      int i=0;
      while( i<=100) {
        try {
            Thread.sleep(10);
            progressBar.setValue(i);
            i++;
            if(i==100) {
              this.dispose();
            }
        } catch (InterruptedException e){
            this.dispose();
        }
      }
  }
}