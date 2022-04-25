package view.screens;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ReadOnlyModel;

/**
 * This class represents the welcome screen. The screen contains the name of the
 * developers along with an option to start the game, using existing
 * configuration file or using a new configuration file and the option to quit
 * the game.
 *
 */
public class WelcomeScreenImpl extends JFrame implements Screen {

  /**
   * serialVersionUID attribute to remember versions of a Serializable class to
   * verify that a loaded class and the serialized object are compatible.
   */
  private static final long serialVersionUID = 1L;
  private final ReadOnlyModel model;
  private final JMenu menu;
  private final JMenu submenu;
  private final JMenuItem currLayout;
  private final JMenuItem newLayout;
  private final JMenuItem quit;
  private final JMenuBar mb;
  private final JFileChooser fileChooser;
  private final JLabel bgImage;
  private final JLabel welcomeCredits;

  /**
   * This class represents the constructor of this class.
   * 
   * @param model - the Read Only Model.
   */
  public WelcomeScreenImpl(ReadOnlyModel model) {
    JLabel bgImage1;
    this.model = model;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLayout(new BorderLayout());
    try {
      InputStream in = ClassLoader.getSystemClassLoader()
          .getResourceAsStream("assets/marvel_latest.jpg");
      BufferedImage image = ImageIO.read(in);
      bgImage1 = new JLabel(new ImageIcon(image));
    } catch (IOException e) {
      bgImage1 = new JLabel();
    }
    bgImage = bgImage1;
    this.add(bgImage);
    bgImage.setLayout(new FlowLayout());
    welcomeCredits = new JLabel("<html><h1>Welcome to the game.</h1><h3>Credits : "
        + "</h3><p>Aashi Shrimal<br>Gowtham Potnuru</p></html>");
    bgImage.add(welcomeCredits);

    welcomeCredits.setForeground(Color.black);
    mb = new JMenuBar();
    menu = new JMenu("Start");
    submenu = new JMenu("Start New Game");
    currLayout = new JMenuItem("Current Layout");
    newLayout = new JMenuItem("New Layout");
    quit = new JMenuItem("Quit");
    fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
    fileChooser.setFileFilter(filter);

    submenu.add(currLayout);
    submenu.add(newLayout);
    menu.add(submenu);
    menu.add(quit);
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
