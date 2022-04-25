package view.screens;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import model.ReadOnlyModel;

/**
 * This class represents the main screen of the game. This screen contains the
 * graphical representation of the world and the instructions to play the game.
 *
 */
public class GameScreenImpl extends JFrame implements Screen {

  /**
   * serialVersionUID attribute to remember versions of a Serializable class to
   * verify that a loaded class and the serialized object are compatible.
   */
  private static final long serialVersionUID = 1L;
  private final ReadOnlyModel model;
  private final JLabel map;
  private final JLabel playerName;
  private final JLabel hints;
  private final JPanel gamePanel;
  private final JPanel instructionPanel;

  /**
   * This is the constructor for this class.
   * 
   * @param model - this is the read only model.
   */
  public GameScreenImpl(ReadOnlyModel model) {
    super();
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Border hintBorder = BorderFactory.createTitledBorder(blackline, "Hints");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel hintsPanel = new JPanel(new GridLayout(2, 0));
    hintsPanel.setBorder(hintBorder);
    JLabel playerName = new JLabel();
    playerName.setHorizontalAlignment(SwingConstants.LEFT);
    playerName.setFont(new Font("MONOSPACE", Font.BOLD, 18));
    playerName.setForeground(Color.DARK_GRAY);
    JLabel hints = new JLabel();
    hints.setHorizontalAlignment(SwingConstants.CENTER);
    hintsPanel.add(playerName);
    hintsPanel.add(hints);
    Border instBorder = BorderFactory.createTitledBorder(blackline, "Instructions");
    instructionPanel = new JPanel();
    instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
    instructionPanel.setBorder(instBorder);
    JLabel i1 = new JLabel("Move Pet",
        new ImageIcon(getClass().getClassLoader().getResource("assets/m.png")), JLabel.LEFT);
    JLabel i2 = new JLabel("Pick Up an Item",
        new ImageIcon(getClass().getClassLoader().getResource("assets/p.png")), JLabel.LEFT);
    JLabel i3 = new JLabel("Attack Target",
        new ImageIcon(getClass().getClassLoader().getResource("assets/k.png")), JLabel.LEFT);
    JLabel i4 = new JLabel("Look Around",
        new ImageIcon(getClass().getClassLoader().getResource("assets/a.png")), JLabel.LEFT);
    JLabel i5 = new JLabel("Move Player",
        new ImageIcon(getClass().getClassLoader().getResource("assets/mouse.png")), JLabel.LEFT);
    instructionPanel.add(i1);
    instructionPanel.add(i2);
    instructionPanel.add(i3);
    instructionPanel.add(i4);
    instructionPanel.add(i5);
    JPanel gamePanel = new JPanel(new GridLayout(1, 0));
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new GridLayout(2, 0));
    infoPanel.add(hintsPanel);
    infoPanel.add(instructionPanel);
    JScrollPane pane = new JScrollPane(gamePanel);
    JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, pane, infoPanel);
    sl.setOrientation(SwingConstants.VERTICAL);
    sl.setResizeWeight(0.75);
    this.add(sl);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) size.getWidth();
    int height = (int) size.getHeight();
    JLabel map = new JLabel();
    map.setIcon(new ImageIcon((Image) model.createGraphicalRepresentation()));
    gamePanel.add(map);
    this.setSize(width, height);
    sl.setResizeWeight(0.75);

    this.map = map;
    this.playerName = playerName;
    this.hints = hints;
    this.gamePanel = gamePanel;
    this.model = model;
  }

  @Override
  public void setFeatures(Features f) {
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        f.handleMouseClick(e.getX(), e.getY());
      }
    });

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        Map<Character, Runnable> keyPressMap = new HashMap<>();

        keyPressMap.put('p', () -> f.pickUpItem());
        keyPressMap.put('m', () -> f.movePet());
        keyPressMap.put('k', () -> f.attack());
        keyPressMap.put('a', () -> f.lookAround());

        Character c = e.getKeyChar();
        Runnable r = keyPressMap.get(c);
        r.run();
      }
    });
  }

  @Override
  public void showScreen() {
    try {
      InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("assets/Game.png");
      BufferedImage image = ImageIO.read(in);
      ImageIcon icon = new ImageIcon(image);

      String information = "The Objective of the game is to kill the Target.\n"
          + "Press the buttons on the keyboard to choose your turn. \n"
          + "Use mouse to move to move the player from one room to another on your turn.";
      JOptionPane.showMessageDialog(null, information, "Welcome to the World",
          JOptionPane.INFORMATION_MESSAGE, icon);
      refresh();
      this.setVisible(true);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot Read File!");
    }
  }

  @Override
  public void hideScreen() {
    this.setVisible(false);
  }

  @Override
  public void refresh() {
    map.setIcon(new ImageIcon((Image) model.createGraphicalRepresentation()));
    playerName.setText(model.playerInTurn() + "is in turn : ");
    hints.setText(model.getCluesForTurn());
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
