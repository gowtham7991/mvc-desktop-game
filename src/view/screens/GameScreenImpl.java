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
import model.ReadOnlyModel;

public class GameScreenImpl extends JFrame implements Screen {

  private ReadOnlyModel m;
  private JLabel map;
  private JLabel playerName;
  private JLabel hints;
  private JPanel gamePanel;

  public GameScreenImpl(ReadOnlyModel m) {
    super();
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

    JLabel playerName = new JLabel();
    JLabel hints = new JLabel();

    hintsPanel.add(playerName);
    hintsPanel.add(hints);

    JButton btn3 = new JButton("3");

    infoPanel.setLayout(new GridLayout(2, 0));

    infoPanel.add(hintsPanel);
    infoPanel.add(btn3);
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
        if (e.getKeyChar() == 'w') {
          f.pickUpItem();
        } else if (e.getKeyChar() == 'a') {
          f.movePet();
        } else if (e.getKeyChar() == 's') {
          f.attack();
        } else if (e.getKeyChar() == 'd') {
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
      ImageIcon icon = new ImageIcon(ImageIO.read(new File("res/player1.png")));
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
    playerName.setText(m.getTurn());
    hints.setText(m.getCluesForTurn());
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
