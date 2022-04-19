package view.screens;

import controller.Features;
import javax.swing.*;
import model.Model;
import model.ReadOnlyModel;

public class StartScreenImpl extends JFrame implements Screen {
  private ReadOnlyModel m;

  public StartScreenImpl(ReadOnlyModel m) {
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
