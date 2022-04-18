package view.screens;

import controller.Features;
import javax.swing.*;
import model.Model;

public class StartScreenImpl extends JFrame implements StartScreen{
  private Model m;

  public StartScreenImpl(Model m) {
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
