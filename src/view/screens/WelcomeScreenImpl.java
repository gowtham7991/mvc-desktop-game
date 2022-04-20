package view.screens;

import controller.Features;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ReadOnlyModel;

public class WelcomeScreenImpl extends JFrame implements Screen{
  private ReadOnlyModel m;
  private JMenu menu, submenu;
  private final JMenuItem i1, i2, i3, i4;
  private final JMenuBar mb;
  private final JFileChooser fileChooser;
  private File file;

  public WelcomeScreenImpl(ReadOnlyModel m) {
    this.m = m;
    mb=new JMenuBar();
    menu=new JMenu("Start");
    submenu=new JMenu("Start New Game");
    i1=new JMenuItem("Current Layout");
    i2=new JMenuItem("New Layout");
    i3=new JMenuItem("Quit");
    i4=new JMenuItem("Help");
    fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
    fileChooser.setFileFilter(filter);

    submenu.add(i1); submenu.add(i2);
    menu.add(submenu);
    menu.add(i3); menu.add(i4);
    mb.add(menu);
    this.setJMenuBar(mb);
    this.setSize(500,600);
    this.setLayout(null);
    this.setLocation(380, 70);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void setFeatures(Features f) {
    i1.addActionListener(l -> f.startGame());
    i2.addActionListener(l -> f.handleGameFileUpload());
    i3.addActionListener(l -> f.exit());
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
