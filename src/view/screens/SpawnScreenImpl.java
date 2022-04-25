package view.screens;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.ReadOnlyModel;

public class SpawnScreenImpl extends JFrame implements Screen {
  private final ReadOnlyModel m;
  private final JButton addPlayerBtn;
  private final JButton addCompPlayerBtn;
  private final JButton startGameBtn;

  public SpawnScreenImpl(ReadOnlyModel m) {
    super();
    this.m = m;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());
    JLabel bgImage = new JLabel(new ImageIcon(getClass().getClassLoader()
        .getResource("assets/welcome_screen_wallpaper.png")));
    this.add(bgImage);
    bgImage.setLayout(new FlowLayout());

    addPlayerBtn = new JButton("Add Player");
    addCompPlayerBtn = new JButton("Add Computer Player");
    startGameBtn = new JButton("Begin Game");

    bgImage.add(addPlayerBtn);
    bgImage.add(addCompPlayerBtn);
    bgImage.add(startGameBtn);
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
