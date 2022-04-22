package view.screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
//ww  w  .  j av a2s  . com
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//public class ImagePanel {
//
//  public static void main(String[] args) {
//    ImagePanel panel = new ImagePanel(
//        new ImageIcon("res/bg.png").getImage());
//
//    JFrame frame = new JFrame();
//    frame.getContentPane().add(panel);
//    frame.pack();
//    frame.setVisible(true);
//  }
//}

public class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(720, 180);
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(720,1080);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}
