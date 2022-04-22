package view.screens;

import controller.Features;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.ReadOnlyModel;

public class GameScreenImpl extends JFrame implements Screen {

  private ReadOnlyModel m;
  private JLabel map;
  private JLabel playerName;
  private JLabel hints;
  private JPanel gamePanel;
  private final Border blackline;
  private final Border instBorder;
  private final Border hintBorder;
  private final JPanel instructionPanel;
  

  public GameScreenImpl(ReadOnlyModel m) {
    super();
    blackline = BorderFactory.createLineBorder(Color.black);
    instBorder = BorderFactory.createTitledBorder(blackline,"Instructions");
    hintBorder = BorderFactory.createTitledBorder(blackline, "Hints");
    //setSize(720, 1080);
    Dimension size
        = Toolkit.getDefaultToolkit().getScreenSize();

    // width will store the width of the screen
    int width = (int)size.getWidth();

    // height will store the height of the screen
    int height = (int)size.getHeight();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel gamePanel = new JPanel(new GridLayout(1, 0));

    JPanel infoPanel = new JPanel();
    JPanel hintsPanel = new JPanel(new GridLayout(2, 0));

    hintsPanel.setBorder(hintBorder);
    JLabel playerName = new JLabel();
    playerName.setHorizontalAlignment(SwingConstants.CENTER);
    playerName.setFont(new Font("MONOSPACE", Font.BOLD, 18));
    playerName.setForeground(Color.BLUE);
    JLabel hints = new JLabel();
    hints.setHorizontalAlignment(SwingConstants.CENTER);

    hintsPanel.add(playerName);
    hintsPanel.add(hints);
    
    instructionPanel = new JPanel();
    instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
    instructionPanel.setBorder(instBorder);
    JLabel i1 = new JLabel("Move Pet",new ImageIcon("res/m.png"),JLabel.LEFT);
    JLabel i2 = new JLabel("Pick Up an Item",new ImageIcon("res/p.png"),JLabel.LEFT);
    JLabel i3 = new JLabel("Attack Target",new ImageIcon("res/k.png"),JLabel.LEFT);
    JLabel i4 = new JLabel("Look Around",new ImageIcon("res/a.png"),JLabel.LEFT);
    JLabel i5 = new JLabel("Move Player",new ImageIcon("res/mouse.png"),JLabel.LEFT);
    instructionPanel.add(i1);
    instructionPanel.add(i2);
    instructionPanel.add(i3);
    instructionPanel.add(i4);
    instructionPanel.add(i5);

    infoPanel.setLayout(new GridLayout(2, 0));

    infoPanel.add(hintsPanel);
    infoPanel.add(instructionPanel);
    JScrollPane pane = new JScrollPane(gamePanel);
    JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, pane, infoPanel);

    // set Orientation for slider
    sl.setOrientation(SwingConstants.VERTICAL);
    sl.setResizeWeight(0.75);

    // add panel
    this.add(sl);
    JLabel map = new JLabel();
    map.setIcon(new ImageIcon((Image) m.createGraphicalRepresentation()));
    gamePanel.add(map);
    this.setSize(width, height);
    sl.setResizeWeight(0.75);

    this.map = map;
    this.playerName = playerName;
    this.hints = hints;
    this.gamePanel = gamePanel;
    this.m = m;
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

    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
          f.pickUpItem();
        } else if (e.getKeyChar() == 'm') {
          f.movePet();
        } else if (e.getKeyChar() == 'k') {
          f.attack();
        } else if (e.getKeyChar() == 'a') {
          f.lookAround();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
  }

  @Override
  public void showScreen() {
    try {
      ImageIcon icon = new ImageIcon(ImageIO.read(new File("res/Game.png")));
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
  public void setModel(ReadOnlyModel m) {
    this.m = m;
  }

  @Override
  public void refresh() {
    map.setIcon(new ImageIcon((Image) m.createGraphicalRepresentation()));
    playerName.setText(m.playerInTurn() + "is in turn : ");
    hints.setText(m.getCluesForTurn());
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
