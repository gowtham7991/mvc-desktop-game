package view.screens;

import controller.Features;
import javax.swing.*;
import model.Model;
import model.ReadOnlyModel;

public class SplashScreenImpl extends JFrame implements SplashScreen{

  private ReadOnlyModel m;
  public SplashScreenImpl(ReadOnlyModel m) {
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
  public void setModel(ReadOnlyModel m) {
    this.m = m;
  }
}
