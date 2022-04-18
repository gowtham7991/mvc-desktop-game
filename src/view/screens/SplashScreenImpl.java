package view.screens;

import controller.Features;
import javax.swing.*;
import model.Model;

public class SplashScreenImpl extends JFrame implements SplashScreen{

  private Model m;
  public SplashScreenImpl(Model m) {
    this.m = m;
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
  public void setModel(Model m) {
    this.m = m;
  }
}
