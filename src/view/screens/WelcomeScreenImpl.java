package view.screens;

import controller.Features;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ReadOnlyModel;

public class WelcomeScreenImpl extends JFrame implements Screen {

  private final ReadOnlyModel m;
  private final JMenu menu, submenu;
  private final JMenuItem currLayout, newLayout, quit, help;
  private final JMenuBar mb;
  private final JFileChooser fileChooser;
  private final JLabel bgImage;
  private final JLabel welcomeCredits;

  public WelcomeScreenImpl(ReadOnlyModel m) {
    JLabel bgImage1;
    this.m = m;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLayout(new BorderLayout());
    try {
      InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("assets/marvel_latest.jpg");
      BufferedImage image = ImageIO.read(in);
      bgImage1 = new JLabel(new ImageIcon(image));
    } catch (IOException e) {
      bgImage1 = new JLabel();
    }
    bgImage = bgImage1;
    this.add(bgImage);
    bgImage.setLayout(new FlowLayout());
    welcomeCredits = new JLabel("<html><h1>Welcome to the game.</h1><h3>Credits : </h3><p>Aashi Shrimal<br>Gowtham Potnuru</p></html>");
    bgImage.add(welcomeCredits);

    welcomeCredits.setForeground(Color.black);
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
  public void refresh() {

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void quit() {
    this.dispose();
  }
}
