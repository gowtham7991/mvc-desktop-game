package view.screens;

import controller.Features;

public interface GameScreen {

  void setFeatures(Features f);
  void showScreen();
  void hideScreen();
  void setModel();
}
