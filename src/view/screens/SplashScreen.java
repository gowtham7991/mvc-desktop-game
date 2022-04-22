package view.screens;
import javax.swing.*;
import java.awt.*;
 
public class SplashScreen extends JFrame {
   /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JFrame frame;
   private Color customColor;
   private String text = " ";
   private String c = " ";
    JLabel title;
    JLabel credits;
    JLabel image=new JLabel(new ImageIcon("res/marvel.gif"));
    JProgressBar progressBar=new JProgressBar();
    public SplashScreen()
    {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        runningPBar();
        customColor = new Color(211,211,243);
    }
    public void createGUI(){
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setLayout(null);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(customColor);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
    }
    public void addImage(){
      image.setSize(600,400);//Setting size of the image
      frame.add(image);//Adding image to the frame
  }

    public void addText()
    {
      title = new JLabel();
      credits = new JLabel();
      title.setText(text);
      title.setFont(new Font("Hack",Font.BOLD,20));//Setting font size of text
      title.setForeground(Color.BLUE);//Setting foreground color
      frame.getContentPane().add(title);//adding text to the frame
      title.setBounds(180, 60, 300, 80);//Setting size and location
      
      credits.setText(c);
      credits.setFont(new Font("Hack",Font.BOLD,20));//Setting font size of text
      credits.setForeground(Color.BLACK);//Setting foreground color
      frame.getContentPane().add(credits);//adding text to the frame
      credits.setBounds(185, 160, 300, 180);//Setting size and location
      frame.setVisible(true);
    }
    public void addProgressBar(){
      progressBar.setBounds(140,360,300,20);
      progressBar.setBorderPainted(true);
      progressBar.setStringPainted(true);
      progressBar.setBackground(Color.WHITE);
      progressBar.setForeground(Color.BLACK);
      progressBar.setValue(0);
      frame.add(progressBar);
  }
    public void runningPBar(){
      int i=0;

      while( i<=100)
      {
          try{
              Thread.sleep(10);
              progressBar.setValue(i);
              i++;
              if(i==100) {
                frame.dispose();      
              } 
          }catch(Exception e){
              e.printStackTrace();
          }

      }
  }
}