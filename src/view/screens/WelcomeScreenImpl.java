package view.screens;

import controller.Features;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ReadOnlyModel;

public class WelcomeScreenImpl extends JFrame implements Screen {
  private ReadOnlyModel m;
  private JMenu menu, submenu;
  private final JMenuItem currLayout, newLayout, quit, help;
  private final JMenuBar mb;
  private final JFileChooser fileChooser;
  private File file;
  private String c = "<html>Credits:  {Developers} <br> Aashi Shrimal <br> Gowtham Potnuru </html>";
  private String greet = "<html> Welcome to the Game! </html>";
  private JLabel credits;
  private JLabel greeting;
  JLabel label;
  Icon icon;
  JButton b1;
  JLabel l1;
  JLabel l2;

  public WelcomeScreenImpl(ReadOnlyModel m) {
    try {
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setLayout(new BorderLayout());
      InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("assets/marvel_latest.jpg");
      BufferedImage image = ImageIO.read(in);
      JLabel bgImage = new JLabel(new ImageIcon(image));
      this.add(bgImage);
      bgImage.setLayout(new FlowLayout());

      l1=new JLabel("<html><h1>Welcome to the game.</h1><h3>Credits : </h3><p>Aashi Shrimal<br>Gowtham Potnuru</p></html>");
      bgImage.add(l1);

      l1.setForeground(Color.black);
      mb = new JMenuBar();
      menu = new JMenu("Start");
      submenu = new JMenu("Start New Game");
      currLayout = new JMenuItem("Current Layout");
      newLayout = new JMenuItem("New Layout");
      quit = new JMenuItem("Quit");
      help = new JMenuItem("Help");
      fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
      fileChooser.setFileFilter(filter);

      submenu.add(currLayout);
      submenu.add(newLayout);
      menu.add(submenu);
      menu.add(quit);
      menu.add(help);
      mb.add(menu);

      this.setJMenuBar(mb);
      this.m = m;
    } catch (IOException io) {
      throw new IllegalArgumentException("");
    }

//    mainPanel.add(l1, BorderLayout.CENTER);
//    mainPanel.add(l2, BorderLayout.PAGE_END);



//    icon = new ImageIcon("res/assets/gif.gif");
//    label = new JLabel(icon);
//    label.setBounds(50, 120, 400, 280);
//

//    credits = new JLabel();
//    credits.setText(c);
//    credits.setFont(new Font("Hack", Font.BOLD, 15));
//    credits.setForeground(Color.BLACK);
//    credits.setBounds(170, 400, 200, 120);
//
//    greeting = new JLabel();
//    greeting.setText(greet);
//    greeting.setFont(new Font("Hack", Font.BOLD, 25));
//    greeting.setForeground(Color.DARK_GRAY);
//    greeting.setBounds(125, 10, 250, 120);
//

//    this.setJMenuBar(mb);
//    this.setSize(500, 600);
//    this.setLayout(null);
//    this.setLocation(380, 70);
//    this.add(label);
//    this.add(greeting);
//    this.add(credits);
//    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void setFeatures(Features f) {
    currLayout.addActionListener(l -> f.startGame());
    newLayout.addActionListener(l -> f.handleGameFileUpload());
    quit.addActionListener(l -> f.exit());
  }

  @Override
  public void showScreen() {
    new SplashScreen();
    this.setVisible(true);
  }

  @Override
  public void hideScreen() {
    this.setVisible(false);
  }

  @Override
  public void setModel(ReadOnlyModel m) {
    this.m = m;
  }

  @Override
  public void refresh() {

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
