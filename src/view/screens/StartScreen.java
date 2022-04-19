package view.screens;

import controller.Features;
import model.Model;
import model.ReadOnlyModel;

public interface StartScreen {
  void setFeatures(Features f);
  void showScreen();
  void hideScreen();
  void setModel(ReadOnlyModel m);
}
