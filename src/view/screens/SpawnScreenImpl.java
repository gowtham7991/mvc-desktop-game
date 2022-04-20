package view.screens;

import controller.Features;
import java.awt.*;
import javax.swing.*;
import model.ReadOnlyModel;

public class SpawnScreenImpl extends JFrame implements Screen {
  private ReadOnlyModel m;
  private JPanel p1;
  private JPanel p;
  private JButton addPlayerBtn;
  private JButton addCompPlayerBtn;
  private JButton startGameBtn;

  public SpawnScreenImpl(ReadOnlyModel m) {
    super();
    this.m = m;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //create panel
    p1 = new JPanel();
    p = new JPanel();
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    p.setAlignmentY(Component.CENTER_ALIGNMENT);

    //create buttons
    addPlayerBtn = new JButton("Add Player");
    addCompPlayerBtn = new JButton("Add Computer Player");
    startGameBtn = new JButton("Begin Game");
    addPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    addCompPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    startGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

    //add buttons to panel
    p.add(addPlayerBtn);
    p.add(addCompPlayerBtn);
    p.add(startGameBtn);


    JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, p, p1);
    sl.setOrientation(SwingConstants.VERTICAL);
    sl.setResizeWeight(0.75);
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

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
