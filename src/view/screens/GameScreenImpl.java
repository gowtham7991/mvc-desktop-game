package view.screens;

import controller.Features;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.Model;
import model.ReadOnlyModel;

public class GameScreenImpl extends JFrame implements Screen {

  private ReadOnlyModel m;

  public GameScreenImpl(ReadOnlyModel m) {
    super();
    this.m = m;
    //setSize(720, 1080);
    Dimension size
        = Toolkit.getDefaultToolkit().getScreenSize();

    // width will store the width of the screen
    int width = (int)size.getWidth();

    // height will store the height of the screen
    int height = (int)size.getHeight();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel gamePanel = new JPanel(new GridLayout(1, 0));
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println(m.getSpaceBasedOnCoordinates(e.getX(), e.getY()));

      }
    });
    JPanel infoPanel = new JPanel();
    JPanel hintsPanel = new JPanel(new GridLayout(2, 0));

    JLabel playerName = new JLabel(m.getTurn());
    JLabel hints = new JLabel(m.getCluesForTurn());

    hintsPanel.add(playerName);
    hintsPanel.add(hints);

    JButton btn1 = new JButton("1");
    JButton btn2 = new JButton("2");
    JButton btn3 = new JButton("3");
    JTextArea t1;
    JTextArea t2;

    t1 = new JTextArea();
    t2 = new JTextArea(10, 10);


    infoPanel.setLayout(new GridLayout(3, 0));

    JPanel c1 = new JPanel();
    JPanel c2 = new JPanel();

    // set texts
    t1.setText("this is first text area");
    t2.setText("this is second text area");
    // add text area to panel

    infoPanel.add(hintsPanel);
    infoPanel.add(btn3);
    JScrollPane pane = new JScrollPane(gamePanel);
    JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, pane, infoPanel);


    // set Orientation for slider
    sl.setOrientation(SwingConstants.VERTICAL);
    sl.setResizeWeight(0.8);

    // add panel
    this.add(sl);

    // set the size of frame
    this.setSize(width, height);
    this.setVisible(true);
    // add panel
    JLabel map = new JLabel(new ImageIcon((Image) m.createGraphicalRepresentation()));

    gamePanel.add(map);

    this.add(sl);

  }

  @Override
  public void setFeatures(Features f) {

  }

  @Override
  public void showScreen() {

  }

  @Override
  public void hideScreen() {

  }


  @Override
  public void setModel(ReadOnlyModel m) {
    this.m = m;

  }
}
