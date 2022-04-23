package view.screens;

import controller.Features;
import java.awt.*;
import model.ReadOnlyModel;

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
  private JButton addPlayerBtn;
  private JButton addCompPlayerBtn;
  private JButton startGameBtn;

  public SpawnScreenImpl(ReadOnlyModel m) {
    super();
    this.m = m;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());
    JLabel bgImage = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("assets/welcome_screen_wallpaper.png")));
    this.add(bgImage);
    bgImage.setLayout(new FlowLayout());


    // create buttons
    addPlayerBtn = new JButton("Add Player");
    addCompPlayerBtn = new JButton("Add Computer Player");
    startGameBtn = new JButton("Begin Game");

    bgImage.add(addPlayerBtn);
    bgImage.add(addCompPlayerBtn);
    bgImage.add(startGameBtn);

//    addPlayerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
//    addCompPlayerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
//    startGameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);


    // add buttons to panel
//    p.add(Box.createHorizontalGlue());
//    p.add(addPlayerBtn);
//    p.add(addCompPlayerBtn);
//    p.add(startGameBtn);
//    p.add(Box.createHorizontalGlue());


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
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
