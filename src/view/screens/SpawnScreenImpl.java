package view.screens;

import controller.Features;
import model.ReadOnlyModel;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class SpawnScreenImpl extends JFrame implements Screen {
  private ReadOnlyModel m;
  private JPanel p1;
  private final JPanel p;
  private JButton addPlayerBtn;
  private JButton addCompPlayerBtn;
  private JButton startGameBtn;
  private List<String> players;

  public SpawnScreenImpl(ReadOnlyModel m) {
    super();
    this.m = m;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  ImagePanel panel = new ImagePanel(
      new ImageIcon("res/new2b.jpg").getImage());
  
    // create panel
    p1 = new JPanel();
    p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
    p = new JPanel();
    panel.add(p);
    p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
    p.setBounds(250, 380, 390, 40);

    // create buttons
    addPlayerBtn = new JButton("Add Player");
    addCompPlayerBtn = new JButton("Add Computer Player");
    startGameBtn = new JButton("Begin Game");
    addPlayerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
    addCompPlayerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
    startGameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

  
    // add buttons to panel
    p.add(Box.createHorizontalGlue());
    p.add(addPlayerBtn);
    p.add(addCompPlayerBtn);
    p.add(startGameBtn);
    p.add(Box.createHorizontalGlue());

    JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, panel, p1);
    sl.setOrientation(SwingConstants.VERTICAL);
    sl.setResizeWeight(0.85);
    this.add(sl);
    this.setSize(300, 300);
    this.add(sl);
    
  }

  @Override
  public void setFeatures(Features f) {
    addPlayerBtn.addActionListener(l -> f.addPlayer());
    addCompPlayerBtn.addActionListener(l -> f.addComputerPlayer());
    startGameBtn.addActionListener(l -> f.begin());
  }

  @Override
  public void showScreen() {
    setVisible(true);
  }

  @Override
  public void hideScreen() {
    setVisible(false);
  }

  @Override
  public void setModel(ReadOnlyModel m) {
    this.m = m;
  }

  @Override
  public void refresh() {
    p1.removeAll();
    players = m.getPlayers();
    for (String s : players) {
      PlayerCardView pl = new PlayerCardView(s);
      p1.add(pl);
    }
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
